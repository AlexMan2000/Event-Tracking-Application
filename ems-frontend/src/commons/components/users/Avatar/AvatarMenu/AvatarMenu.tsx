  import { useIntl } from "react-intl"
import styles from "./AvatarMenu.module.less"
import { useSelector } from "react-redux";
import { Divider } from "@mui/material";
import {
    useDispatch,
    selectInterview,
    selectUser,
    setDialogOpen,
    setExitCallbackKey,
  } from '@/store/index'
import UserIcon from "@/assets/images/user-icon.svg"
import CreditCard from "@/assets/images/credit-card.svg"
import LogOutIcon from "@/assets/images/sign-out-icon.svg"
import DataLynnIcon from "@/assets/images/img-icon-blue.png"
import { MouseEventHandler, useEffect, useState } from "react";
import Avatar from "../AvatarIcon/AvatarIcon";
import { selectCopilotSlice } from "@/store/index";
import { exitCallbacks } from "@/store/slice/copilotSlice/exitCallbacks";
import { useNavigate } from "react-router-dom";

interface OptionItem {
    text: string;
    callback: MouseEventHandler<HTMLDivElement>;
    img?: string;
}


const AvatarMenu = () => {
    const intl = useIntl();
    const dispatch = useDispatch()
    const navigate = useNavigate();
    const {locale} = useSelector(selectInterview);
    const {dialogToggleable} = useSelector(selectCopilotSlice);
    const [visible, setIsVisible] = useState<boolean>(false);

    const {
        subscription_type
        , subscription_end_date
        , points
        , last_name
        , first_name
        , email
        , avatar
    } = useSelector(selectUser);

    useEffect(() => {
        setIsVisible(true);
        return () => {
            setIsVisible(false);
        }
    }, [])


    const handleExit = (key: keyof typeof exitCallbacks) => {
        exitCallbacks[key]({dispatch, navigate, locale});
    };
      
    // 跳转按钮, 登出
    const LoggedInOptionItem: OptionItem[] = [
        {
            img: UserIcon,
            text: intl.formatMessage({ id: 'ad.profile' }),
            callback: (event) => {
              event.preventDefault();
              if (dialogToggleable) {
                dispatch(setDialogOpen(true));
                dispatch(setExitCallbackKey('userProfile'));
              } else {
                handleExit('userProfile');
              }
            },
          },
          {
            img: CreditCard,
            text: intl.formatMessage({ id: 'ad.subscribe_button' }),
            callback: (event) => {
              event.preventDefault();
              if (dialogToggleable) {
                dispatch(setDialogOpen(true));
                dispatch(setExitCallbackKey('subscription'));
              } else {
                handleExit('subscription');
              }
            },
          },
          {
            img: LogOutIcon,
            text: intl.formatMessage({ id: 'ad.log_out' }),
            callback: (event) => {
              event.preventDefault();
              if (dialogToggleable) {
                dispatch(setDialogOpen(true));
                dispatch(setExitCallbackKey('logout'));
              } else {
                handleExit('logout');
              }
            },
          },
    ]

    const memberTypeToText = {
        "premium": intl.formatMessage({id: "ad.subscribe_member.premium"}),
        "free": intl.formatMessage({id: "ad.subscribe_member.free"})
    }


    const formatDate = (dateString: string | null, locale: 'zh' | 'en'): string => {
        if (locale == "en") {
            if (!dateString) {
                return '-'
            }
            
            const date = new Date(dateString)
        
            const options: Intl.DateTimeFormatOptions = {
                year: 'numeric',
                month: 'short',
                day: 'numeric',
            }
        
            // 使用 Intl.DateTimeFormat 格式化日期
            const formatter = new Intl.DateTimeFormat('en-US', options)
        
            // 获取格式化后的日期字符串
            const formattedDate = formatter.format(date)
        
            // 将月份缩写后加一个点
            const parts = formattedDate.split(' ')
            parts[0] = parts[0] + '.'
        
            return parts.join(' ')
        } else {
            if (!dateString) {
                return ''
            }
            const date = new Date(dateString);
            const formatter = new Intl.DateTimeFormat('zh-CN', {
                year: 'numeric',
                month: 'numeric',
                day: 'numeric'
              });
              
              // Format the date
              const formattedDate = formatter.format(date);
              const parts = formattedDate.split("/");
              return `${parts[0]}年 ${parts[1]} 月 ${parts[2]} 日`
        }
        
    }

    return (
        <div className={
            Array.from(
                [styles.container,
                 visible && styles["visible"]]
            ).filter(Boolean)
            .join(' ')
            }
            >
            
            <div className={styles.header}>
                <div className={styles.avatarContainer}>
                    <Avatar avatar={avatar} name={last_name}></Avatar>
                </div>
                <div className={styles.info}>
                    <div className={styles.fullName}>
                        {
                            locale == "zh" ? 
                            `${last_name} ${first_name}`:
                            `${first_name} ${last_name}`
                        }
                    </div>
                    <div className={styles.email}>
                        {email}
                    </div>
                </div>
            </div>
            
            <div className={styles.account}>
                <div className={styles.listItem}>
                    <div className={styles.label}>
                        {intl.formatMessage({id: "ad.subscribe_label"})}
                    </div>
                    <div className={styles.subsTextContainer}>
                        <div className={styles.subsText}>
                        {memberTypeToText[subscription_type]}
                        </div>
                    </div>
                </div>
                <div className={styles.listItem}>
                    <div className={styles.label}>
                        {intl.formatMessage({id: "ad.subscribe_expire"})}
                    </div>
                    <div className={styles.label}>
                        {formatDate(subscription_end_date, locale)}
                    </div>
                </div>
                <div className={styles.listItem}>
                    <div className={styles.label}>
                    {intl.formatMessage({id: "ad.points"})}
                    </div>
                    <div className={styles.pointGroup}>
                        <img src={DataLynnIcon} className={styles.pointImg}></img>
                        <div className={styles.label}>{points}</div>
                    </div>
                </div>
            </div>
            <Divider orientation="horizontal" sx={{ width:"288px"}}></Divider>
            <div className={styles.optionMenu}>
                {
                    LoggedInOptionItem.map((elem: OptionItem, index: number) => {
                        return (
                        <div className={styles.optionItem} key={elem.toString() + index}
                        onClick={elem.callback}>
                            <img src={elem.img} className={styles.itemIcon}></img>
                            <div className={styles.itemText}>{elem.text}</div>
                        </div>
                        )
                    })
                }
            </div>
        </div>
    )
}

  

export default AvatarMenu;