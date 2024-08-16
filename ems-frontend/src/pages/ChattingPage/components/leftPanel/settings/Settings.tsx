import React, { CSSProperties } from 'react';
import "./Settings.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import { useChatBotModalContext } from '../../ChattingModalContext';
import AvatarIcon from '@/commons/components/users/Avatar/AvatarIcon/AvatarIcon';
import BirdIcon from "@/assets/little-bird.png"
import NotificationsIcon from "@/assets/settings/notifications-grey.svg"
import PrivacyIcon from "@/assets/settings/privacy-grey.svg"
import SecurityIcon from "@/assets/settings/security-grey.svg"
import ThemeIcon from "@/assets/settings/theme-grey.svg"
import ChatWallpaperIcon from "@/assets/settings/chat-wallpaper-grey.svg"
import RequestAccountInfoIcon from "@/assets/settings/request-account-info-grey.svg"
import KeyBoardShortCutIcon from "@/assets/settings/keyboard-shortcut-grey.svg"
import HelpIcon from "@/assets/settings/help-grey.svg"
import ImageBox from '@/commons/components/medias/ImageBox';
import { Divider } from '@mui/material';
import { encodeToBase64 } from '@/commons/utils/encoderHandler';
import BackNavigation from '@/commons/components/navigation/BackNavigation';
import { SubPageBaseInterface } from '../interfaces';

interface SettingsProps extends SubPageBaseInterface {
  // Define your props here
  style?: CSSProperties
  className?: string
}


const SettingsMenuItems = [
  {
      key:"notifications",
      label: "Notifications",
      src: NotificationsIcon,
  },
  {
      key:"privacy",
      label: "Privacy",
      src: PrivacyIcon,
  }
  ,{
      key:"security",
      label: "Security",
      src: SecurityIcon,
  }
  ,{
      key:"theme",
      label: "Theme",
      src: ThemeIcon,
  },{
      key:"chatWallpaper",
      label: "Chat Wallpaper",
      src: ChatWallpaperIcon,
  },{
      key:"requestAccountInfo",
      label: "Request Account Info",
      src: RequestAccountInfoIcon,
  },{
      key:"keyboardShortcuts",
      label: "Keyboard Shortcuts",
      src: KeyBoardShortCutIcon,
  },{
      key:"help",
      label: "Help",
      src: HelpIcon,
  }
]

const Settings: React.FC<SettingsProps> = ({onSubPage, style, className}: SettingsProps) => {
  
  const {currentRoute, setCurrentRoute} = useChatBotModalContext();

  const findSubRouteFullPath = (currentRoute: string, menuName: string):string => {
    return currentRoute.split("/").concat(menuName).join("/");
  }


  return (
    <div className={classNamesArgs("settings-container", className)} style={{...style}}>
      <BackNavigation navKey={"settings"} navName={"Settings"}/>
      <div className={classNamesArgs("settings-avatar-line", className)}>
        <AvatarIcon avatar={BirdIcon} name={""} style={{width: "70px", height: "70px"}}/>
        <div className={classNamesArgs("settings-avatar-line-name-group", className)}>
          <span className={classNamesArgs("settings-avatar-line-name-group-header", className)}>Shreyansh shah</span>
          <span className={classNamesArgs("settings-avatar-line-name-group-text", className)}>Exploring</span>
        </div>
      </div>
      <div className={classNamesArgs("settings-menu-group", className)}>
        {
          SettingsMenuItems.map((elem, index) => (
            <div key={encodeToBase64(elem)+index} className={classNamesArgs("settings-menu-item", className)}
              onClick = {() => {
                const newSubPageFullPath = findSubRouteFullPath(currentRoute, elem.key);
                if (onSubPage) {
                  onSubPage(newSubPageFullPath);
                }
              }}
            >
              <div className={classNamesArgs("settings-menu-item-top", className)}>
                <ImageBox src={elem.src} width={25} height={25}></ImageBox>
              {elem.label}</div>
              <Divider orientation='horizontal' sx={{width: "100%", height:"1px", bgColor: "#B4B4B4", border:"1px 0px 0px 0px solid #B4B4B4"}}></Divider>
            </div>
          ))
        }


      </div>
    </div>
  );
};

export default Settings;