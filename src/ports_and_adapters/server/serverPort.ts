//export interface ServerPort {
  //(): {start: (port: number) => void};
//}

export type ServerPort = () => {
    start: (port: number) => void,
}

