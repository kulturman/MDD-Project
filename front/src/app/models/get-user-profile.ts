export interface GetUserProfile {
  username: string;
  email: string;
  subscriptions: {
    id: number;
    title: string;
    description: string;
  }[]
}
