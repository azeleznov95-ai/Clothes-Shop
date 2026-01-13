package com.example.personalfinancetracker.service;

import com.example.personalfinancetracker.dto.TransactionRequest;
import com.example.personalfinancetracker.dto.TransactionResponse;
import com.example.personalfinancetracker.exeptions.EmptyCategory;
import com.example.personalfinancetracker.exeptions.IncorrectLoginOrPasswordException;
import com.example.personalfinancetracker.exeptions.InvalidToken;
import com.example.personalfinancetracker.model.Category;
import com.example.personalfinancetracker.model.FinanceTransaction;
import com.example.personalfinancetracker.model.User;
import com.example.personalfinancetracker.repository.CategoryRepository;
import com.example.personalfinancetracker.repository.FinanceTransactionRepository;
import com.example.personalfinancetracker.security.JWToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {
    private final JWToken JWT;
    private final CategoryRepository categoryRepository;
    private final FinanceTransactionRepository transactionRepository;
    TransactionService(JWToken JWToken,FinanceTransactionRepository financeTransactionRepository,CategoryRepository categoryRepository){
        this.transactionRepository =financeTransactionRepository;
        this.JWT =JWToken;
        this.categoryRepository=categoryRepository;
    }
    public FinanceTransaction create(TransactionRequest transactionRequest,String token){
        if (!JWT.isValid(token)){
            throw new InvalidToken("Invalid Token");
        }
        Long userId= JWT.extractUserId(token);
        String login = JWT.extractLogin(token);
        FinanceTransaction financeTransaction = new FinanceTransaction();
        financeTransaction.setCreatedAt(LocalDateTime.now());
        Optional<Category> categoryEntity = categoryRepository
                .findById(transactionRequest
                        .getCategoryId());
        if (categoryEntity.isEmpty()){
            throw new EmptyCategory("Bad Request,cause category is Empty");
        }

        Category category=categoryEntity.get();

        financeTransaction.setCategory(category);
        financeTransaction.setAmount(transactionRequest.getAmount());
        financeTransaction.setDescription(transactionRequest.getDescription());
        transactionRepository.save(financeTransaction);
        return financeTransaction;
    }

}
