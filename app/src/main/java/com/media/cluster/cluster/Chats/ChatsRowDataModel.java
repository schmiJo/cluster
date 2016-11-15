package com.media.cluster.cluster.Chats;



public class ChatsRowDataModel {
    public int pictureID, newMessageCounter;
    public String title, clustername, lastMessage, messageTime;

    //Enum for the Status Icon for each Chat Row
    public enum MessageIcon {SENDING, SEND, RECEIVED, READ, NULL}
    public MessageIcon messageIcon;

    //Enum for the Extra Icon for each Chat Row
    public enum ChatExtraIcon {NEW, MUTE, NULL}
    public ChatExtraIcon chatExtraIcon;

}
