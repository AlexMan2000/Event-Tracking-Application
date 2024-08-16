import React, { CSSProperties } from 'react';
import "./MessageList.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"
import BirdIcon from "@/assets/little-bird.png"
import ReactIcon from "@/assets/react.svg"
import MessageLine from '../message/Message';
import { encodeToBase64 } from '@/commons/utils/encoderHandler';



interface MessageListProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}

const MessageList: React.FC<MessageListProps> = ({style, className}: MessageListProps) => {
  
  
  const messages = [
    {
      id: '1',
      type: 'text',
      sender: 'Alice',
      timestamp: new Date(2023, 7, 12, 14, 45),
      content: 'Hello, how are you?',
    },
    {
      id: '2',
      type: 'text',
      sender: 'Bob',
      timestamp: new Date(2023, 7, 12, 14, 46),
      content: 'I am doing well, thanks!',
    },
    {
      id: '3',
      type: 'image',
      sender: 'Alice',
      timestamp: new Date(2023, 7, 12, 14, 47),
      imageUrl: BirdIcon,
    },
    {
      id: '4',
      type: 'text',
      sender: 'Alice',
      timestamp: new Date(2023, 7, 12, 14, 48),
      content: 'Check out this image I found!',
    },
    {
      id: '5',
      type: 'file',
      sender: 'Bob',
      timestamp: new Date(2023, 7, 12, 14, 49),
      fileName: 'report.pdf',
      fileUrl: 'https://example.com/report.pdf',
    },
    {
      id: '6',
      type: 'text',
      sender: 'Alice',
      timestamp: new Date(2023, 7, 12, 14, 50),
      content: 'Thanks for the report!',
    },
    {
      id: '7',
      type: 'image',
      sender: 'Alice',
      timestamp: new Date(2023, 7, 12, 14, 51),
      imageUrl: ReactIcon,
    },
    {
      id: '8',
      type: 'file',
      sender: 'Bob',
      timestamp: new Date(2023, 7, 12, 14, 52),
      fileName: 'data.csv',
      fileUrl: 'https://example.com/data.csv',
    },
    {
      id: '9',
      type: 'text',
      sender: 'Alice',
      timestamp: new Date(2023, 7, 12, 14, 53),
      content: 'Here is the updated data.',
    },
    {
      id: '10',
      type: 'image',
      sender: 'Bob',
      timestamp: new Date(2023, 7, 12, 14, 54),
      imageUrl: ReactIcon,
    },
  ];
  
  
  
  
  
  return (
    <div className={classNamesArgs("messages-container", className)}>
      {
        messages.map((message, index) => {
          return <MessageLine key={encodeToBase64(message)+index} message={message} />
        })
      }
      
    </div>
  );
};

export default MessageList;