module order.concurrency {
    requires order.storage;
    requires order.processing;
    // JavaFaker є автоматичним модулем, імпортуємо його
    requires javafaker;
    requires java.sql;
}