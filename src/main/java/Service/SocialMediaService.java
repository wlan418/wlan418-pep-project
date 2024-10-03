package Service;

import java.util.List;

import DAO.SocialMediaDAO;
import Model.Account;
import Model.Message;

public class SocialMediaService {
    SocialMediaDAO sdao;

    public SocialMediaService() {
        sdao = new SocialMediaDAO();
    }
    public SocialMediaService(SocialMediaDAO sdao) {
        this.sdao = sdao;
    }
    public Account createAccount(Account account) {
        return sdao.createAccount(account);
    }

    public Account verifyAccount(Account account) {
        return sdao.verifyAccount(account);
    }

    public Message createMessage(Message message) {
        return sdao.createMessage(message);
    }

    public List<Message> getAllMessages() {
        return sdao.getAllMessages();
    }

    public Message getMessage(int id) {
        return sdao.getMessage(id);
    }

    public Message deleteMessage(int id) {
        return sdao.deleteMessage(id);
    }

    public Message updateMessage(int id, String message) {
        if (getMessage(id)==null)
            return null;
        else
            return sdao.updateMessage(id, message);
    }

    public List<Message> getAllMessagesFromUser(int account_id) {
        return sdao.getAllMessagesFromUser(account_id);
    }
}
