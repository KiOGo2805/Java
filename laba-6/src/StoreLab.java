private static final AtomicInteger successfulPurchases = new AtomicInteger(0);

private static final ReentrantReadWriteLock catalogLock = new ReentrantReadWriteLock();

private static final ReentrantLock purchaseLock = new ReentrantLock();

private static final Condition restockCondition = purchaseLock.newCondition();

private static final Semaphore checkoutRegisters = new Semaphore(2, true);

private static final Map<String, Integer> inventory = new HashMap<>();

private static final Object deliveryBell = new Object();

void main() throws InterruptedException {
    inventory.put("GPU", 2);
    inventory.put("CPU", 1);

    IO.println("Магазин відкрито.");

    Thread b1 = new Thread(new Buyer("Степан", "GPU"));
    Thread b2 = new Thread(new Buyer("Олег", "GPU"));
    Thread b3 = new Thread(new Buyer("Анна", "GPU"));
    Thread b4 = new Thread(new Buyer("Іван", "CPU"));

    b1.setPriority(Thread.MAX_PRIORITY);

    b1.start();
    b2.start();
    b3.start();
    b4.start();

    Thread.sleep(1500);

    Thread supplier = getSupplier();

    Thread.sleep(500);
    synchronized (deliveryBell) {
        IO.println("Менеджер дзвонить постачальнику");
        deliveryBell.notifyAll();
    }

    Thread.sleep(500);
    b4.interrupt();

    b1.join();
    b2.join();
    b3.join();
    b4.join();
    supplier.join();

    IO.println("\nЗавершено. Всього успішних покупок: " + successfulPurchases.get());
}

private static Thread getSupplier() {
    Thread supplier = new Thread(() -> {
        try {
            synchronized (deliveryBell) {
                IO.println("Постачальник чекає на виклик...");
                deliveryBell.wait();
            }

            Thread.sleep(1000);
            purchaseLock.lock( );
            try {
                inventory.put("GPU", inventory.get("GPU") + 1);
                IO.println("Постачальник привіз ще одну GPU!");
                restockCondition.signalAll();
            } finally {
                purchaseLock.unlock();
            }
        } catch (InterruptedException e) {
            System.err.println("Потік було примусово перервано: " + e.getMessage());
        }
    });
    supplier.start();
    return supplier;
}

static class Buyer implements Runnable {
    private final String name;
    private final String item;

    public Buyer(String name, String item) {
        this.name = name;
        this.item = item;
    }

    public void run() {
        Thread.currentThread().setName("Thread-" + name);

        try {
            catalogLock.readLock().lock();
            try {
                if (!inventory.containsKey(item)) return;
            } finally {
                catalogLock.readLock().unlock();
            }

            checkoutRegisters.acquire();
            try {
                IO.println(name + " підійшов до каси за " + item);

                if (purchaseLock.tryLock(10, TimeUnit.SECONDS)) {
                    try {
                        while (inventory.get(item) == 0) {
                            IO.println(name + " чекає на поставку " + item + "...");
                            restockCondition.await();
                        }

                        purchaseLock.lockInterruptibly();
                        try {
                            int stock = inventory.get(item);
                            if (stock > 0) {
                                Thread.sleep(2000);
                                inventory.put(item, stock - 1);
                                successfulPurchases.incrementAndGet();
                                IO.println(name + " успішно купив " + item + "! Залишок: " + (stock - 1));
                            }
                        } finally {
                            purchaseLock.unlock();
                        }
                    } finally {
                        purchaseLock.unlock();
                    }
                } else {
                    IO.println(name + " не дочекався касира і пішов.");
                }
            } finally {
                checkoutRegisters.release();
            }

        } catch (InterruptedException e) {
            IO.println(name + " був примусово зупинений");
        }
    }
}