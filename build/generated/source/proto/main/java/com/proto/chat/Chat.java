// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: chat/chat.proto

package com.proto.chat;

public final class Chat {
  private Chat() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_chatapp_messageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_chatapp_messageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_chatapp_messageResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_chatapp_messageResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017chat/chat.proto\022\007chatapp\032\037google/proto" +
      "buf/timestamp.proto\"/\n\016messageRequest\022\014\n" +
      "\004from\030\001 \001(\t\022\017\n\007message\030\002 \001(\t\"j\n\017messageR" +
      "esponse\022-\n\ttimestamp\030\001 \001(\0132\032.google.prot" +
      "obuf.Timestamp\022(\n\007request\030\002 \001(\0132\027.chatap" +
      "p.messageRequest2N\n\013ChatService\022?\n\004chat\022" +
      "\027.chatapp.messageRequest\032\030.chatapp.messa" +
      "geResponse\"\000(\0010\001B\022\n\016com.proto.chatP\001b\006pr" +
      "oto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.TimestampProto.getDescriptor(),
        });
    internal_static_chatapp_messageRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_chatapp_messageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_chatapp_messageRequest_descriptor,
        new java.lang.String[] { "From", "Message", });
    internal_static_chatapp_messageResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_chatapp_messageResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_chatapp_messageResponse_descriptor,
        new java.lang.String[] { "Timestamp", "Request", });
    com.google.protobuf.TimestampProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
