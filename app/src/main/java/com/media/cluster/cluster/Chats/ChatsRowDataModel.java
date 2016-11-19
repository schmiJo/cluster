package com.media.cluster.cluster.Chats;



 class ChatsRowDataModel {
     int pictureID, newMessageCounter;
     String title, clustername, lastMessage, messageTime;

    //Enum for the Status Icon for each Chat Row
     int messageIcon;

      static final int SENDING = 0;
      static final int SEND= 1;
      static final int RECEIVED= 2;
      static final int READ= 3;
     public static final int NULL= 4;
    //Enum for the Extra Icon for each Chat Row
      static final int NEW  = 0;
      static final int MUTE = 1;
     int  chatExtraIcon;

}
