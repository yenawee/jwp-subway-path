package subway.domain;

public class Passenger {
    private static final int TEENAGER_MIN = 13;
    private static final int TEENAGER_MAX = 18;
    private static final int CHILD_MIN = 6;
    private static final int CHILD_MAX = 12;

    private final Long age;

    public Passenger(Long age) {
        validate(age);
        this.age = age;
    }

    private void validate(Long age) {
        if (age < 0) {
            throw new IllegalArgumentException("나이는 양의 정수로 입력해주세요");
        }
    }

    public boolean isChild() {
        return age >= CHILD_MIN && age <= CHILD_MAX;
    }

    public boolean isTeenager() {
        return age >= TEENAGER_MIN && age <= TEENAGER_MAX;
    }
}
