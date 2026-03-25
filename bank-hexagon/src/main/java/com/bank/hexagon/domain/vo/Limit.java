package com.bank.hexagon.domain.vo;

import com.bank.hexagon.domain.exception.BankValidationException;

public record Limit(
        Double dailyTransactionLimit,
        Double dailyTransferLimit,
        Double dailyWithdrawalLimit
) {
    public Limit {
        validate(dailyTransactionLimit, dailyTransferLimit, dailyWithdrawalLimit);
    }

    private void validate(Double dailyTransactionLimit, Double dailyTransferLimit, Double dailyWithdrawalLimit) {
        StringBuilder sb = new StringBuilder();

        if(dailyTransactionLimit < 0) {
            sb.append("Daily transaction limit must be greater than 0.");
        }
        if(dailyTransferLimit < 0) {
            sb.append("Daily transfer limit must be greater than 0.");
        }
        if(dailyWithdrawalLimit < 0) {
            sb.append("Daily withdraw limit must be greater than 0.");
        }

        BankValidationException.when(!sb.isEmpty(), sb.toString());

    }
}
