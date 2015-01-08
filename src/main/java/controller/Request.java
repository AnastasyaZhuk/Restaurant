package controller;

public enum Request {
    SHOW_CATEGORY("show category"), SHOW_DISHES("show dishes"), NEW_CATEGORY("new category"), NEW_FOOD("new food"),
    UPDATE_CATEGORY("update category"), UPDATE_FOOD("update food"), DELETE_CATEGORY("delete category"),
    DELETE_FOOD("delete food"), NULL_REQUEST("");
    private String requestValue;

    Request(String request) {
        requestValue = request;
    }

    Request() {

    }

    public String getRequestValue() {
        return requestValue;
    }

    @Override
    public String toString() {
        return requestValue;
    }

    static public Request getRequest(String vRequest) {
        for (Request request : Request.values()) {
            if (request.getRequestValue().equals(vRequest)) {
                vRequest = request.getRequestValue();
                return request;
            }
        }
        throw new RuntimeException("unknown type");
    }
}