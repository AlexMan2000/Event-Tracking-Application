import React, { CSSProperties, useState } from 'react';
import "./MessageInput.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"
import ImageBox from '@/commons/components/medias/ImageBox';
import AttachIcon from "@/assets/attachment.svg"
import EmojiIcon from "@/assets/emoji.svg"
import SendIcon from "@/assets/send-button.svg"

interface MessageInputProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}

const MessageInput: React.FC<MessageInputProps> = ({style, className}: MessageInputProps) => {
  
  const [inputValue, setInputValue] = useState<string>("");
  const [isFocused, setIsFocused] = useState<boolean>(false);
  

  const handleOpenAttachment = () => {

  }


  const handleOpenEmoji = () => {

  }


  const handleSubmit = () => {
    console.log(inputValue);
  }

  const handleChange = (e) => {
    e.preventDefault();
    setInputValue(e.target.value);
  }
  
  return (
    <div className={classNamesArgs("message-input-container", className)} style={{...style}}>
         <div className={classNamesArgs("message-input-box", className)}>
            <div className={classNamesArgs("message-input-group", className)}>
              <ImageBox src={AttachIcon}
                className={classNamesArgs("message-input-attach", className)}
                onClick={handleOpenAttachment}
              ></ImageBox>
              <input className={classNamesArgs("message-input", className, isFocused? "wipe":"show")}
                onFocus={()=> {setIsFocused(true)}}
                onBlur={()=> {setIsFocused(false)}}
                onChange={handleChange}
                value={inputValue}
                placeholder={isFocused ? "" : "Write Messages..."}
              ></input>
            </div>

            <ImageBox src={EmojiIcon} 
            className={classNamesArgs("message-input-emoji", className)}
            onClick={handleOpenEmoji}></ImageBox>
         </div>
         <ImageBox src={SendIcon} width={30} height={30}
          style={{
            backgroundColor: "#5B96F7",
            padding: "12px",
            boxSizing: "content-box",
            borderRadius: "12px"
          }}
          onClick={handleSubmit}
          className={classNamesArgs("message-input-send", className)}
         ></ImageBox>
    </div>
  );
};

export default MessageInput;