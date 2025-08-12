package model.dto;

public record TransactionResponse(

        int id,
        String senderName,
        String receiverName,
        double amount,
        String date

) {
}
