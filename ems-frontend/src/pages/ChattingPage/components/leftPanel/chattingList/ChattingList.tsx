import React, { CSSProperties } from 'react';
import "./ChattingList.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"
import UserBox from './components/UserBox';
import { encodeToBase64 } from '@/commons/utils/encoderHandler';
import { Divider } from '@mui/material';
import DashCircle from "@/assets/circle-dash.svg"
import ImageBox from '@/commons/components/medias/ImageBox';
import SearchInput from '@/commons/components/forms/SearchInput/SearchInput';
import ArchiveIcon from "@/assets/archive-icon-black.svg"

interface ChattingListProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}

const ChattingList: React.FC<ChattingListProps> = ({style, className}: ChattingListProps) => {
  
  
    const ListData = [
        {
            username: "Red Falcon",
            messageHistory: [
                {
                    sender: "Red Falcon",
                    timestamp: new Date(2023, 7, 12, 17, 30),
                    type: "text",
                    data: "Did you finish the assignment?",
                },
                {
                    sender: "Alex",
                    timestamp: new Date(2023, 7, 12, 17, 32),
                    type: "text",
                    data: "Yes, just submitted it.",
                },
                {
                    sender: "Red Falcon",
                    timestamp: new Date(2023, 7, 12, 17, 33),
                    type: "text",
                    data: "Great! Let's review it tomorrow.",
                }
            ],
            isPinned: true
        },
        {
            username: "Yellow Lion",
            messageHistory: [
                {
                    sender: "Yellow Lion",
                    timestamp: new Date(2023, 7, 12, 18, 0),
                    type: "text",
                    data: "Want to join the basketball game tonight?",
                },
                {
                    sender: "Alex",
                    timestamp: new Date(2023, 7, 12, 18, 2),
                    type: "text",
                    data: "Sure, count me in!",
                },
                {
                    sender: "Yellow Lion",
                    timestamp: new Date(2023, 7, 12, 18, 3),
                    type: "text",
                    data: "Awesome! See you at 7 PM.",
                }
            ],
            isPinned: true
        },
        {
            username: "Orange Tiger",
            messageHistory: [
                {
                    sender: "Orange Tiger",
                    timestamp: new Date(2023, 7, 12, 19, 15),
                    type: "text",
                    data: "How's the project going?",
                },
                {
                    sender: "Alex",
                    timestamp: new Date(2023, 7, 12, 19, 17),
                    type: "text",
                    data: "It's progressing well, but I could use some help with the design.",
                },
                {
                    sender: "Orange Tiger",
                    timestamp: new Date(2023, 7, 12, 19, 18),
                    type: "text",
                    data: "No problem, I can help you with that tomorrow.",
                }
            ],
            isPinned: false
        },
        {
            username: "Purple Elephant",
            messageHistory: [
                {
                    sender: "Purple Elephant",
                    timestamp: new Date(2023, 7, 12, 20, 5),
                    type: "text",
                    data: "Did you see the latest episode of the series?",
                },
                {
                    sender: "Alex",
                    timestamp: new Date(2023, 7, 12, 20, 7),
                    type: "text",
                    data: "Yes, it was amazing!",
                },
                {
                    sender: "Purple Elephant",
                    timestamp: new Date(2023, 7, 12, 20, 9),
                    type: "text",
                    data: "I can't wait for the next one.",
                }
            ],
            isPinned: false
        },
        {
            username: "White Swan",
            messageHistory: [
                {
                    sender: "White Swan",
                    timestamp: new Date(2023, 7, 12, 21, 20),
                    type: "text",
                    data: "Are you coming to the dance practice?",
                },
                {
                    sender: "Alex",
                    timestamp: new Date(2023, 7, 12, 21, 22),
                    type: "text",
                    data: "Yes, I'll be there in 10 minutes.",
                },
                {
                    sender: "White Swan",
                    timestamp: new Date(2023, 7, 12, 21, 23),
                    type: "text",
                    data: "Great, see you soon!",
                }
            ],
            isPinned: false
        },
        {
            username: "Black Panther",
            messageHistory: [
                {
                    sender: "Black Panther",
                    timestamp: new Date(2023, 7, 12, 22, 10),
                    type: "text",
                    data: "Have you checked the new restaurant in town?",
                },
                {
                    sender: "Alex",
                    timestamp: new Date(2023, 7, 12, 22, 12),
                    type: "text",
                    data: "Not yet, but I've heard good things about it.",
                },
                {
                    sender: "Black Panther",
                    timestamp: new Date(2023, 7, 12, 22, 13),
                    type: "text",
                    data: "Let's go there this weekend!",
                }
            ],
            isPinned: false
        },
        {
            username: "Golden Eagle",
            messageHistory: [
                {
                    sender: "Golden Eagle",
                    timestamp: new Date(2023, 7, 12, 23, 0),
                    type: "text",
                    data: "What are your plans for the holiday?",
                },
                {
                    sender: "Alex",
                    timestamp: new Date(2023, 7, 12, 23, 2),
                    type: "text",
                    data: "I'm planning a trip to the mountains.",
                },
                {
                    sender: "Golden Eagle",
                    timestamp: new Date(2023, 7, 12, 23, 3),
                    type: "text",
                    data: "Sounds exciting! Enjoy your trip!",
                }
            ],
            isPinned: false
        },
        {
            username: "Silver Dolphin",
            messageHistory: [
                {
                    sender: "Silver Dolphin",
                    timestamp: new Date(2023, 7, 13, 8, 15),
                    type: "text",
                    data: "Can you send me the report?",
                },
                {
                    sender: "Alex",
                    timestamp: new Date(2023, 7, 13, 8, 17),
                    type: "text",
                    data: "Sure, I'll send it over in a few minutes.",
                },
                {
                    sender: "Silver Dolphin",
                    timestamp: new Date(2023, 7, 13, 8, 18),
                    type: "text",
                    data: "Thanks, appreciate it!",
                }
            ],
            isPinned: false
        },
        {
            username: "Bronze Wolf",
            messageHistory: [
                {
                    sender: "Bronze Wolf",
                    timestamp: new Date(2023, 7, 13, 9, 30),
                    type: "text",
                    data: "Are you up for a hike this Saturday?",
                },
                {
                    sender: "Alex",
                    timestamp: new Date(2023, 7, 13, 9, 32),
                    type: "text",
                    data: "Absolutely, I could use some fresh air!",
                },
                {
                    sender: "Bronze Wolf",
                    timestamp: new Date(2023, 7, 13, 9, 33),
                    type: "text",
                    data: "Great, let's meet at 7 AM.",
                }
            ],
            isPinned: false
        },
        {
            username: "Emerald Snake",
            messageHistory: [
                {
                    sender: "Emerald Snake",
                    timestamp: new Date(2023, 7, 13, 10, 50),
                    type: "text",
                    data: "Can you help me with the code review?",
                },
                {
                    sender: "Alex",
                    timestamp: new Date(2023, 7, 13, 10, 52),
                    type: "text",
                    data: "Sure, let's do it now.",
                },
                {
                    sender: "Emerald Snake",
                    timestamp: new Date(2023, 7, 13, 10, 53),
                    type: "text",
                    data: "Thanks, really appreciate it!",
                }
            ],
            isPinned: false
        },
        {
            username: "Crimson Bear",
            messageHistory: [
                {
                    sender: "Crimson Bear",
                    timestamp: new Date(2023, 7, 13, 11, 20),
                    type: "text",
                    data: "Are you attending the conference next week?",
                },
                {
                    sender: "Alex",
                    timestamp: new Date(2023, 7, 13, 11, 22),
                    type: "text",
                    data: "Yes, I've already registered.",
                },
                {
                    sender: "Crimson Bear",
                    timestamp: new Date(2023, 7, 13, 11, 23),
                    type: "text",
                    data: "Awesome, let's catch up there!",
                }
            ],
            isPinned: false
        }
        
    ]


    const ListDataProcessed = ListData.map((data, index) => {
        const lastMessage = (!data.messageHistory || data.messageHistory.length == 0) ? null:data.messageHistory[ data.messageHistory.length - 1];
        if (lastMessage) {
            const timestampMinute = lastMessage.timestamp.getMinutes();
            let timestampMinuteString ;

            if (timestampMinute < 10) {
                timestampMinuteString = `0${timestampMinute}`
            } else {
                timestampMinuteString = +timestampMinute;
            }
            return {
                "username": data.username,
                "lastMessage": lastMessage.data,
                "lastMessageTS": `${lastMessage.timestamp.getHours()}:${timestampMinuteString}`,
                "isPinned": data.isPinned
            }
        } else {
            return {

            }
        }
        
    })


    const ListDataProcessedPinned = ListDataProcessed.filter((data, index) => {
        return data.isPinned;
    })

  
    return (
    <div className={classNamesArgs("chatting-list-container", className)} style={{...style}}>
      <div className={classNamesArgs("chatting-list-header", className)}>
        <div className={classNamesArgs("chatting-list-header-line", className)}>
            <div className={classNamesArgs("chatting-list-header-text", className)}>
                Chats
            </div>
            <ImageBox src={DashCircle} width={32} height={32}></ImageBox>
        </div>
        <div className={classNamesArgs("chatting-list-header-search", className)}>
            <SearchInput ></SearchInput>
        </div>
        <div className={classNamesArgs("chatting-list-header-archive", className)}>
            <ImageBox src={ArchiveIcon} width={24} height={24}></ImageBox>
            Archive
        </div>
      </div>
      <Divider orientation="horizontal" sx={{width: "400px"}}></Divider>
      <div className={classNamesArgs("chatting-list-wrapper", className)}>
        <div className={classNamesArgs("chatting-list-pinned", className)}>
            <div className={classNamesArgs("chatting-list-pinned-title", className)}>
                Pinned
            </div>
            {
                ListDataProcessedPinned.map((elem, index) => 
                <UserBox 
                    key={encodeToBase64(elem) + index} 
                    username={elem.username!} 
                    lastMessage={elem.lastMessage} 
                    lastMessageTS={elem.lastMessageTS}>
                </UserBox>
            )}
        </div>
        <div className={classNamesArgs("chatting-list-all-chats", className)}>
        <div className={classNamesArgs("chatting-list-all-chats-title", className)}>
            All Chats
        </div>
            {
                ListDataProcessed.map((elem, index) => 
                <UserBox 
                    key={encodeToBase64(elem) + index} 
                    username={elem.username!} 
                    lastMessage={elem.lastMessage} 
                    lastMessageTS={elem.lastMessageTS}>
                </UserBox>
            )}
        </div>


      </div>
      
    </div>
  );
};

export default ChattingList;