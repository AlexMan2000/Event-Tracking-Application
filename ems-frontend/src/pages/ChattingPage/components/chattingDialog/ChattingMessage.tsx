import React, { CSSProperties } from 'react';
import "./ChattingMessage.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"
import MessageList from './messageList/MessageList';
import MessageInput from './messageInput/MessageInput';
import BirdIcon from "@/assets/little-bird.png"
import AvatarIcon from '@/commons/components/users/Avatar/AvatarIcon/AvatarIcon';
import VideoIcon from "@/assets/video-call.svg"
import ImageBox from '@/commons/components/medias/ImageBox';
import SearchIcon from "@/assets/search-icon-black.svg"
import PhoneIcon from "@/assets/phone-icon-black.svg"
import ArrowDown from "@/assets/arrows/down-arrow-black.svg"
import { Divider } from '@mui/material';
import { encodeToBase64 } from '@/commons/utils/encoderHandler';



interface ChattingMessageProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}

const ChattingMessage: React.FC<ChattingMessageProps> = ({style, className}: ChattingMessageProps) => {
 
  
  const ButtonsList = [
    VideoIcon, PhoneIcon, SearchIcon, "divider", ArrowDown
  ]
 
  return (
    <div className={classNamesArgs("chatting-container", className)} style={{...style}}>
      <div className={classNamesArgs("chatting-header", className)}>
        <div className={classNamesArgs("chatting-header-avatar-group", className)}>
          <AvatarIcon avatar={BirdIcon} name={"Pink Panda"}></AvatarIcon>
          <div className={classNamesArgs("chatting-header-avatar-text", className)}>
            <div className={classNamesArgs("chatting-header-avatar-name", className)}>Pink Panda</div>
            <div className={classNamesArgs("chatting-header-avatar-status", className)}>Online</div>
          </div>
        </div>
        <div className={classNamesArgs("chatting-header-button-group", className)}>
              {ButtonsList.map((elem, index) => 
                elem === "divider"? <Divider 
                key={encodeToBase64(elem) + index}
                orientation='vertical' 
                  sx={
                    {
                      border: "border: 1px solid #B4B4B4",
                      height: "48px",
                      width: "2px"
                  }}></Divider>:
                <div className={classNamesArgs("top-icon", className)}
                    key={encodeToBase64(elem) + index}>
                      <ImageBox src={elem} width={30} height={30} />
                </div>
              )}
        </div>
      </div>
      <MessageList></MessageList>
      <MessageInput></MessageInput>
    </div>
  );
};

export default ChattingMessage;