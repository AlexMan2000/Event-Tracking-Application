import React, { CSSProperties } from 'react';
import "./Message.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"
import ImageBox from '@/commons/components/medias/ImageBox';
import { useSelector } from 'react-redux';
import { selectUser } from '@/store/slice/userSlice/userSlice';



interface Message {
  id: string;
  type: string;
  sender: string;
  timestamp: Date | undefined;
  content?: string;
  imageUrl?: string;
  fileName?: string;
  fileUrl?: string;
}


interface MessageProps {
  // Define your props here
  id: string;
  sender: string;
  timestamp: Date | undefined;
  style?: CSSProperties
  className?: string
}


interface TextMessageProps extends MessageProps {
  content: string; 
}

interface ImageMessageProps extends MessageProps {
  imageUrl: string; // Will send an HTTP request
}

interface FileMessageProps extends MessageProps {
  fileName: string;
  fileUrl: string; // Will send an HTTP request
}

const TextMessage: React.FC<TextMessageProps> = ({id, sender, timestamp, content, style, className}: TextMessageProps) => {

  
  return (
    <div className={classNamesArgs("text-message-wrapper", className)}>
      Message Component
    </div>
  );
};


const ImageMessage: React.FC<ImageMessageProps> = ({id, sender, timestamp, imageUrl, style, className}: ImageMessageProps) => {
  return (
    <div className={classNamesArgs("image-message-wrapper", className)}>
      <ImageBox src={imageUrl}/>
    </div>
  );
};

const FileMessage: React.FC<FileMessageProps> = ({id, sender, timestamp, fileName, fileUrl, style, className}: FileMessageProps) => {
  return (
    <div className={classNamesArgs("file-message-wrapper", className)}>
      Message Component
    </div>
  );
};


const MessageLine: React.FC<{ message: Message }> = ({ message }) => {

  // const curr_user = useSelector(selectUser);

  const curr_user = {
    
    nickname: "Bob"};

  const side = curr_user.nickname === message.sender ? "right" : "left";

  const renderMessage = (message: Message) => {
      switch (message.type) {
          case "text":
              return <TextMessage content={message.content!} key={message.id} {...message} />;
          case "image":
              return <ImageMessage imageUrl={message.imageUrl!} key={message.id} {...message} />;
          case "file":
              return <FileMessage fileName={message.fileName!} fileUrl={message.fileUrl!} key={message.id} {...message} />;
          default:
              return null;
      }
  };

  return <div className={classNamesArgs("chat-message-container", side)}>{renderMessage(message)}</div>;
};


export default MessageLine;