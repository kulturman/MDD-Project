export interface GetUserProfile {
  username: string;
  email: string;
  subscriptions: Subscription[]
}

export interface Subscription {
  id: number;
  title: string;
  description: string;
}
