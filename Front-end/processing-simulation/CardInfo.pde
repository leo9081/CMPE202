class CardInfo {
  int cardId;
  String cardNum;
  String cardCode;
  int cardHolder;
  double cardBalance;
  
  public CardInfo(int id, String num, String code, int holderId, double balance) {
    cardId = id;
    cardNum = num;
    cardCode = code;
    cardHolder = holderId;
    cardBalance = balance;
  }
}
