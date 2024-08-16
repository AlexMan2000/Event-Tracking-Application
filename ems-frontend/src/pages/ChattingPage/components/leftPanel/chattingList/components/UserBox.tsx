import React, { CSSProperties } from 'react';
import "./UserBox.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"
import AvatarIcon from '@/commons/components/users/Avatar/AvatarIcon/AvatarIcon';
import BirdIcon from "@/assets/little-bird.png"
import ImageBox from '@/commons/components/medias/ImageBox';
import ArrowDown from "@/assets/arrows/down-arrow-black.svg"

interface UserBoxProps {
  // Define your props here
  username: string
  lastMessage: any  // subject o change
  lastMessageTS: any 
  style?: CSSProperties
  className?: string
}

const UserBox: React.FC<UserBoxProps> = ({username, lastMessage, lastMessageTS, style, className}: UserBoxProps) => {
  
  
  
  
    
  
  
    return (
    <div className={classNamesArgs("user-box-container", className)} style={{...style}}>
      <AvatarIcon avatar={BirdIcon} name={username}></AvatarIcon>
      <div className={classNamesArgs("user-box-right-group", className)}>
        <div className={classNamesArgs("user-box-title", className)}> 
            <div className={classNamesArgs("user-box-title-text", className)}>{username}</div>
            <div className={classNamesArgs("user-box-title-time", className)}>{lastMessageTS}</div>
        </div>
        <div className={classNamesArgs("user-box-message", className)}>
            <div className={classNamesArgs("user-box-message-text", className)}>
                {lastMessage}
            </div>
            <ImageBox className={classNamesArgs("user-box-message-toggle", className)} src={ArrowDown} width={20} height={20}></ImageBox>
        </div>
      </div>
    </div>
  );
};

export default UserBox;