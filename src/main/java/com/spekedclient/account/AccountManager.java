package com.spekedclient.account;

import com.spekedclient.SpekedClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountManager {
    private final List<Account> accounts = new ArrayList<>();
    private Account activeAccount;

    public void load() {
        // Load accounts from config
        // Add default offline account for testing
        Account offlineAccount = new Account(UUID.randomUUID().toString(), "Player", Account.AccountType.OFFLINE);
        addAccount(offlineAccount);
    }

    public void addAccount(Account account) {
        if (!accounts.contains(account)) {
            accounts.add(account);
        }
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        if (activeAccount == account) {
            switchAccount(accounts.isEmpty() ? null : accounts.get(0));
        }
    }

    public void switchAccount(Account account) {
        if (account == null) return;
        
        if (activeAccount != null) {
            activeAccount.setActive(false);
        }
        
        activeAccount = account;
        account.setActive(true);
        
        // Apply session/authentication
        applyAccountSession(account);
        
        SpekedClient.LOGGER.info("Switched to account: {}", account.getUsername());
    }

    public void refreshToken() {
        if (activeAccount != null && activeAccount.isExpired()) {
            try {
                // Refresh account session based on type
                switch (activeAccount.getType()) {
                    case MICROSOFT:
                        MicrosoftAuth.refresh(activeAccount);
                        break;
                    case ELY_BY:
                        ElyByAuth.refresh(activeAccount);
                        break;
                    case LITTLE_SKIN:
                        LittleSkinAuth.refresh(activeAccount);
                        break;
                    case OFFLINE:
                        // No refresh needed
                        break;
                }
            } catch (Exception e) {
                SpekedClient.LOGGER.warn("Failed to refresh account", e);
            }
        }
    }

    private void applyAccountSession(Account account) {
        try {
            switch (account.getType()) {
                case MICROSOFT:
                    MicrosoftAuth.applySession(account);
                    break;
                case ELY_BY:
                    ElyByAuth.applySession(account);
                    break;
                case LITTLE_SKIN:
                    LittleSkinAuth.applySession(account);
                    break;
                case OFFLINE:
                    OfflineAuth.applySession(account);
                    break;
            }
        } catch (Exception e) {
            SpekedClient.LOGGER.warn("Failed to apply account session", e);
        }
    }

    public Account getActiveAccount() {
        return activeAccount;
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public Optional<Account> getAccount(String username) {
        return accounts.stream()
            .filter(a -> a.getUsername().equals(username))
            .findFirst();
    }
}
