package bank;

import security.key.BankPublicKey;
import security.key.BankSymmetricKey;
import security.*;

public class MobileApp {

    // 아래 과정을 통해서 AppId에 유니크한 ID가 부여된다.
    private String randomUniqueStringGen(){
        return Encryptor.randomUniqueStringGen();
    }
    private final String AppId = randomUniqueStringGen();

    public String getAppId() {
        return AppId;
    }
    private BankSymmetricKey bankSymmetricKey;

    // 모든 transcaction에는 고유의 transId를 할당해야 한다. 아래 프로퍼티를 이용한다.
    // deposit, withdraw message 객체를 생성할 때마다 transId를 증가시켜준다.
    private static int curTransId = 1;
    public void setCurTransId(int id){curTransId=id;}

    private String id, password;
    public MobileApp(String id, String password){
        this.id = id;
        this.password = password;
    }

    // 새로운 String을 생성해서 bankSymmetricKey를 고유하게 생성하고, 이를 다시 publicKey로 암호화한다.
    public Encrypted<BankSymmetricKey> sendSymKey(BankPublicKey publickey){
        //TODO: Problem 1.2
        bankSymmetricKey = new BankSymmetricKey(Encryptor.randomUniqueStringGen());
        return new Encrypted<BankSymmetricKey>(bankSymmetricKey, publickey);
    }


    public Encrypted<Message> deposit(int amount){
        //TODO: Problem 1.2
        Message message = new Message("deposit", this.id, this.password, amount, curTransId);
        setCurTransId(curTransId+1);
        return new Encrypted<Message>(message, bankSymmetricKey);
    }

    public Encrypted<Message> withdraw(int amount){
        //TODO: Problem 1.2
        Message message = new Message("withdraw", this.id, this.password, amount, curTransId);
        setCurTransId(curTransId+1);
        return new Encrypted<Message>(message, bankSymmetricKey);
    }

    // boolean 타입으로 나오나? 뭐지?
    public boolean processResponse(Encrypted<Boolean> obj) {
        //TODO: Problem 1.2
        if(obj==null) {
            return false;
        } else {
            return obj.decrypt(bankSymmetricKey);
        }
    }

    public Encrypted<Message> requestCompensate(String question, String answer, int[] transIdList){
        //TODO: Problem 1.3
        String qnA = question+","+answer;
        Message message = new Message("compensate", id, password, qnA, transIdList);
        return new Encrypted<Message>(message, bankSymmetricKey);
    }
}

