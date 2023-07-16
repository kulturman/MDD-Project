export interface ShowArticle {
  id: string;
  createdAt: string;
  title: string;
  content: string;
  theme: {
    id: number;
    name: string
  },
  author: Author,
  comments: {
    id: number,
    content: string,
    author: Author
  }[]
}

interface Author {
  id: number;
  username: string;
}
