package de.aicard.Social;
import de.aicard.account.Account;
import de.aicard.account.AccountList;

public class Chat {

private MessageList chathistory;
private AccountList participants ;

    //Constructor

    public Chat()
    { chathistory = new MessageList();
    participants = new AccountList();
    }
    public Chat(Account _participant,Account _creator, String _message)
    {
        chathistory = new MessageList(_message, _creator);
        participants = new AccountList();
        participants.addPerson(_creator);
        participants.addPerson(_participant);

    }
    //Setter & getter

    public MessageList get_chathistory() { return chathistory; }
    public void set_chathistory(MessageList _chathistory) {
        this.chathistory = _chathistory;
    }

    public AccountList get_participants() { return participants; }
    public void set_participants(AccountList _participant) {
        this.participants = _participant;
    }

    //Functions
    public void addParticipant(Account _participant){
        this.participants.addPerson(_participant);
    }

    public Account getChatCreator(){
        return participants.getPerson(0);
    }

    public void clearHistory(){
        this.chathistory = new MessageList();
    }

    // chats

    public void sendmessage(String _message, Account _sender) {
     Message message = new Message(_message, _sender);
     this.chathistory.addMessage(message);
    }

    //TODO remove message function


}
