package sharypov.OrganizationRestApi.model.status;

/**
 * Доступные статусы платежа
 */
public enum PaymentStatus {

    PROCESSED("PROCESSED", "Исполнено"),
    INSUFFICIENT_FUNDS("INSUFFICIENT FUNDS", "Недостаточно средств");

    private final String status;
    private final String description;

    PaymentStatus(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
