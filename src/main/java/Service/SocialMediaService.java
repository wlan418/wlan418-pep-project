package Service;

import DAO.SocialMediaDAO;
import Model.Account;

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
}
