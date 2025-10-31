package lotto.constants;

public enum PromptMessage {
    PROMPT_PAY_AMOUNT("구입금액을 입력해 주세요.");

    private final String promptMessage;

    PromptMessage(String promptMessage) {
        this.promptMessage = promptMessage;
    }

    public String getMessage() {
        return this.promptMessage;
    }
}
