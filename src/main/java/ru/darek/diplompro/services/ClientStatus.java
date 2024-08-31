package ru.darek.diplompro.services;

public enum ClientStatus {
    ACTIVED(1), BLOCKED(2), ARCHIVED(3), ERROR(4), CREATED(5);
    int statusId;
    private ClientStatus(int statusId) {
        this.statusId = statusId;
    }
    public int getStatusId() {
        return statusId;
    }
}
