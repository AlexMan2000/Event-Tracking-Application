import React, { CSSProperties } from 'react';
import "./SideBar.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import ImageBox from '@/commons/components/medias/ImageBox';
import MessageIcon from "@/assets/message-white.svg"
import BirdIcon from "@/assets/little-bird.png"
import GearIcon from "@/assets/gear-icon-black.svg"
import UsersIcon from "@/assets/users-icon-black.svg"
import PhoneIcon from "@/assets/phone-icon-black.svg"
import Divider from '@mui/material/Divider';
import Switch from '@/commons/components/forms/Switch/Switch';
import AvatarIcon from '@/commons/components/users/Avatar/AvatarIcon/AvatarIcon';
import { encodeToBase64 } from '@/commons/utils/encoderHandler';
import { useChatBotModalContext } from '../ChattingModalContext';
import { findFullPath } from '../ChattingModalContext';

interface SideBarProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}

const TopMenuItems = [ 
    {
        key:"chat",
        src: MessageIcon,
    },
    {
        key:"group",
        src: UsersIcon,
    }
    ,{
        key:"call",
        src: PhoneIcon,
    }
    ,{
        key:"divider",
        src: "none",
    },{
        key:"settings",
        src: GearIcon,
    }
]

const SideBar: React.FC<SideBarProps> = ({style, className}: SideBarProps) => {
  
    const {currentRoute, setCurrentRoute} = useChatBotModalContext();

    const currentActiveMenu = currentRoute.split("/").filter(elem=>elem!="")[0]

    const handleSwitch = (value: boolean) => {
        console.log(value);
    }

    return (
    <div className={classNamesArgs("sidebar-container", className)} style={{...style}}>
      <div className={classNamesArgs("top-group",className)}>
        <div className={classNamesArgs("top-icon", className)}>
             <ImageBox src={BirdIcon} width={30} height={30} />
        </div>
         {
            TopMenuItems.map((elem, index) => {

            if (elem.key === "divider") {
                return (
                    <Divider orientation='horizontal'
                    key={encodeToBase64(elem)+index}
                        sx={
                            {
                                width: "60px",
                                border: "1px solid #B4B4B4"}
                        }
                    ></Divider>
                )
            }   

            const isSelected = currentActiveMenu === elem.key;
            return (
                <div className={classNamesArgs("icon-wrapper",className, isSelected ? "selected":"")}
                 key={encodeToBase64(elem)+index}
                    onClick = {() => {setCurrentRoute(findFullPath(elem.key).path)}}
                    >
                    <ImageBox src={elem.src} width={30} height={30} 
                    style={{
                        filter:`${isSelected ? "invert(1)": "brightness(100%)"}`
                    }}
                    />
                </div>
            )
         })}
      </div>
      <div className={classNamesArgs("bottom-group",className)}>
        <Switch onChange={handleSwitch}></Switch>
        <AvatarIcon avatar={BirdIcon} name={"haha"}></AvatarIcon>
      </div>
    </div>
  );
};

export default SideBar;