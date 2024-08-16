// Careful, must define the context and Provider obj in tsx file.
import React, { createContext, useState, ReactNode, useContext, FC } from 'react';
import ChattingList from './leftPanel/chattingList/ChattingList';
import Settings from './leftPanel/settings/Settings';
import Notifications from "./leftPanel/settings/notifications/Notifications"
import Privacy from './leftPanel/settings/privacy/Privacy';
import Security from "./leftPanel/settings/privacy/Privacy"
import Theme from "./leftPanel/settings/theme/Theme"
import ChatWallPaper from  "./leftPanel/settings/chat-wallpaper/ChatWallPaper"
import RequestAccountInfo from './leftPanel/settings/request-account-info/RequestAccountInfo';
import KeyBoardShortCuts from './leftPanel/settings/keyboard-shortcuts/KeyBoardShortCuts';
import Help from './leftPanel/settings/help/Help';



// Nested Structure
interface Route {
  name: string;
  component: React.FC;
  subRoutes?: Record<string, Route>;
}


// 全局路径配置， nested ADT
const routes: Record<string, Route> = {
  chat: {
    name: "chattingList",
    component: ChattingList,
  },
  group: {
    name: "groupList",
    component: ChattingList
  },
  call: {
    name: "callList",
    component: ChattingList,
  },
  settings: {
    name: 'Settings',
    component: Settings,
    subRoutes: {
      notifications: {
        name: 'Notifications',
        component: Notifications,
      },
      privacy: {
        name: 'Privacy',
        component: Privacy,
        subRoutes: {
          lastSeen: {
            name: 'Last Seen',
            component: Privacy,
          },
          profilePhoto: {
            name: 'Profile Photo',
            component: Privacy,
          },
          // Add more sub-routes here
        },
      },
      security: {
        name: 'Security',
        component: Security,
      },
      theme: {
        name: 'Theme',
        component: Theme,
      },
      chatWallpaper: {
        name: 'Chat Wallpaper',
        component: ChatWallPaper,
      },
      requestAccountInfo: {
        name: 'Request Account Info',
        component: RequestAccountInfo,
      },
      keyboardShortcuts: {
        name: 'Keyboard Shortcuts',
        component: KeyBoardShortCuts,
      },
      help: {
        name: 'Help',
        component: Help,
      },
    },
  },
  profile: {
    name: "profile",
    component: ChattingList
  }
};


interface RouteTreeNode {
  parentPath: string
  path: string // current path from base e.g. basepath/currentNode
  name: string 
  component: React.FC
  childrens?: RouteTreeNode[]
}

const constructParseTreeHelper = (routes:Record<string, Route>, basePath:string): RouteTreeNode[]  => {

    return Object.keys(routes).map((key) => {

      const fullPath = `${basePath}/${key}`
      const currentNode = routes[key]; 
      const node: RouteTreeNode = {
        parentPath: basePath,
        path: fullPath,
        name: key,
        component: currentNode.component        
      }

      if (currentNode.subRoutes) {
        node.childrens = constructParseTreeHelper(currentNode.subRoutes, fullPath);
      }

      return node;
    })
}


const constructParseTree = (routes:Record<string, Route>, basePath:string): RouteTreeNode => {

  const rootNode:RouteTreeNode = {
    parentPath: "",
    path:"",
    name: "root",
    component: ()=><></>,
    childrens: constructParseTreeHelper(routes, basePath)
  }

  return rootNode;
}

const routeTreeRoot = constructParseTree(routes, "");

export const findFullPath = (menuName: string) => {
  return findFullPathHelper(routeTreeRoot, menuName)
}

const findFullPathHelper = (root: RouteTreeNode, menuName: string): {found: boolean, path: string} => {


  if (root.name == menuName) {
    return {
      found: true,
      path: root.path
    };
  }

  if (root.childrens) {
    for (let i = 0; i < root.childrens.length; i++) {
      const currentNode = root.childrens[i];
      const foundState = findFullPathHelper(currentNode, menuName);
      if (foundState.found) {
        return {
          found: true,
          path: foundState.path
        };
      }
    }
  }
 
  return {
    found: false,
    path: ""
  };
}


export const findMenuNode = ( fullPath: string) => {
  return findMenuNodeHelper(routeTreeRoot, fullPath);
}


const findMenuNodeHelper = (root: RouteTreeNode, fullPath: string): RouteTreeNode | null => {

  if (root.path === fullPath) {
    return root;
  }

  if (root.childrens) {
    for (let i = 0; i < root.childrens.length; i++) {
      const currentNode = root.childrens[i];
      const foundNode = findMenuNodeHelper(currentNode, fullPath);
      if (foundNode) {
        return foundNode;
      }
    }
  }
 
  return null;
} 



interface ChatBotModalContextProps {
  routeDS: RouteTreeNode;
  currentRoute: string;
  setCurrentRoute: (pagename: string) => void;
}

// Context creation
const ChatBotModalContext = createContext<ChatBotModalContextProps | undefined>(undefined);

// Provider component
export const ChatBotModalProvider: FC<{ children: ReactNode }> = ({ children }) => {
  const [currentRoute, setCurrentRoute] = useState<string>('/settings' );

  return (
    <ChatBotModalContext.Provider value={{ routeDS: routeTreeRoot, currentRoute, setCurrentRoute }}>
      {children}
    </ChatBotModalContext.Provider>
  );
};

// Custom hook to use the context
export const useChatBotModalContext = () => {
  const context = useContext(ChatBotModalContext);
  if (!context) {
    throw new Error('useChatBotModalContext must be used within a ChatBotModalProvider');
  }
  return context;
};
