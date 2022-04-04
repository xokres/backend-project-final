package academy.mindswap.flight.gatewayemail;

public enum EmailTemplate {
    WELCOME_EMAIL("d-8a8ebe80cbaa40369ac982780c167438"),
    BOOKED_FLIGHT("d-cc16ccb8279442b1a54e1c2faad5ff34"),
    CANCELED_FLIGHT("d-9c901669865246409789a3957187bba0");

    private String name;

    EmailTemplate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
