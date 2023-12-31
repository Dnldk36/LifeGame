public enum Status {
    NONE,       //1
    BORN,       //2
    LIFE,       //1
    DIED;       //2

    public Status step1(int around) {
        switch (this) {
            case NONE -> {
                return (around == 3) ? BORN : NONE;
            }
            case LIFE -> {
                return (around > 3 || around < 2) ? DIED : LIFE;
            }
            default -> {
                return this;
            }
        }
    }

    public Status step2() {
        switch (this) {
            case BORN -> {
                return LIFE;
            }
            case DIED -> {
                return NONE;
            }
            default -> {
                return this;
            }
        }
    }

    public boolean isCell() {
        return this == LIFE || this == DIED;
    }
}
