export interface SubPageBaseInterface {
    onBackPage?: (parentPageFullPath: string) => void;
    onSubPage?: (subPageFullPath: string) => void;
}