module order.processing {
    // Взаємодія між модулями через requires та exports
    requires order.storage;
    exports com.univ.order.processing;
}