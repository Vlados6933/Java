import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        List<Product> catalog = new ArrayList<>();
        catalog.add(new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук", electronics));
        catalog.add(new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном", smartphones));
        catalog.add(new Product(3, "Навушники", 2499.00, "Бездротові навушники", accessories));

        Cart cart = new Cart();
        List<Order> orderHistory = new ArrayList<>(); // Збереження історії замовлень

        while (true) {
            System.out.println("\n--- МЕНЮ ІНТЕРНЕТ-МАГАЗИНУ ---");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Видалити товар з кошика");
            System.out.println("4 - Переглянути кошик");
            System.out.println("5 - Зробити замовлення");
            System.out.println("6 - Історія замовлень");
            System.out.println("7 - Пошук товарів");
            System.out.println("0 - Вийти");
            System.out.print("Ваш вибір: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nКаталог товарів:");
                    for (Product p : catalog) {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.print("Введіть ID товару для додавання: ");
                    int addId = scanner.nextInt();
                    Product toAdd = findProductById(catalog, addId);
                    if (toAdd != null) {
                        cart.addProduct(toAdd);
                        System.out.println("Товар успішно додано до кошика!");
                    } else {
                        System.out.println("Помилка: Товар з таким ID не знайдено.");
                    }
                    break;

                case 3:
                    System.out.print("Введіть ID товару для видалення: ");
                    int removeId = scanner.nextInt();
                    Product toRemove = findProductById(cart.getProducts(), removeId);
                    if (toRemove != null) {
                        cart.removeProduct(toRemove);
                        System.out.println("Товар видалено з кошика!");
                    } else {
                        System.out.println("Помилка: Цього товару немає у вашому кошику.");
                    }
                    break;

                case 4:
                    System.out.println("\n" + cart);
                    break;

                case 5:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Ваш кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order); // Зберігаємо замовлення в історію
                        System.out.println("Замовлення успішно оформлено!\n");
                        System.out.println(order);
                        cart.clear(); // Очищуємо кошик
                    }
                    break;

                case 6:
                    System.out.println("\n--- Історія ваших замовлень ---");
                    if (orderHistory.isEmpty()) {
                        System.out.println("Ви ще не зробили жодного замовлення.");
                    } else {
                        for (int i = 0; i < orderHistory.size(); i++) {
                            System.out.println("\nЗамовлення #" + (i + 1) + ":");
                            System.out.println(orderHistory.get(i));
                        }
                    }
                    break;

                case 7:
                    System.out.print("Введіть ключове слово для пошуку (назва або категорія): ");
                    String keyword = scanner.nextLine().toLowerCase();
                    boolean found = false;
                    System.out.println("\nРезультати пошуку:");
                    for (Product p : catalog) {
                        if (p.getName().toLowerCase().contains(keyword) ||
                                p.getCategory().getName().toLowerCase().contains(keyword)) {
                            System.out.println(p);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("За вашим запитом нічого не знайдено.");
                    }
                    break;

                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин. До побачення!");
                    return;

                default:
                    System.out.println("Невідома опція. Будь ласка, спробуйте ще раз.");
                    break;
            }
        }
    }

    private static Product findProductById(List<Product> list, int id) {
        for (Product p : list) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}