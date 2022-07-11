package com.example.java.async;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
// https://www.baeldung.com/java-completablefuture
@Slf4j
public class CompletableFutureTest {
    public static Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            System.out.println("CompletableFuture processing");
            Thread.sleep(500); // приостановка выполнения на какое-то время
            completableFuture.complete("Hello");
            System.out.println("CompletableFuture completed");

            return null;
        });

        return completableFuture;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Future<String> completableFuture = null;
        System.out.println("before");
        try {
            completableFuture = calculateAsync();
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }

        System.out.println("middle");

        if (completableFuture != null) {
            String result = completableFuture.get();
            System.out.println(result); // "Hello"
        }

        System.out.println("after");
    }
}

@Slf4j
class CompletableFutureTest1 {
    public static void main(String[] args) {
        CompletableFutureTest1Inner completableFutureTest1Inner = new CompletableFutureTest1Inner();

        try {
            completableFutureTest1Inner.main();
        } catch (InterruptedException | ExecutionException e) {
            // оборачиваю в RuntimeException при том что InterruptedException и ExecutionException, такая универсальная обработка
            throw new RuntimeException("async request error ", e);
        }
    }
}

@Slf4j
class CompletableFutureTest1Inner {
    public CompletableFuture<String> calculateAsync() {
        // тут удобно что не надо ничего комплитить а просто выполнить какой-то асинхронный код в коллбеке
         return CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("CompletableFuture processing1");

                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }

                    System.out.println("CompletableFuture completed1");


                    return "Hi mom";
                }
        );
    }

    // тут клево что если пишу throws то должен сверху обработать
    public void main() throws ExecutionException, InterruptedException {
        System.out.println("before1");

        CompletableFuture<String> completableFuture = calculateAsync();

        System.out.println("middle1");

        String result = completableFuture.get();

        System.out.println(result); // "Hi mom"

        System.out.println("after1");
    }

    private void handleAsyncOperation(String orderConfiguration, String engagedParty) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "async req");

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "async req1");

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2);

        try {
            combinedFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("async TestModel and TestModel1 request error ", e);
        }
    }
}