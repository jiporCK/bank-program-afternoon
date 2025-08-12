package model.dto;

public record TransactionRequest(

        int senderId,
        int receiverId,
        double amount

) {
}
