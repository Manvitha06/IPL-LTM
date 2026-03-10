export class Match {
  matchId: number;
  firstTeamId: number;
  secondTeamId: number;
  matchDate: Date;
  venue: string;
  result: string;
  status: string;
  winnerTeamId: number;

  constructor(
    matchId: number,
    firstTeamId: number,
    secondTeamId: number,
    matchDate: Date,
    venue: string,
    result: string,
    status: string,
    winnerTeamId: number
  ) {
    this.matchId = matchId;
    this.firstTeamId = firstTeamId;
    this.secondTeamId = secondTeamId;
    this.matchDate = matchDate;
    this.venue = venue;
    this.result = result;
    this.status = status;
    this.winnerTeamId = winnerTeamId;
  }

  // 👉 Use exact labels and no spaces around colon
  displayInfo(): void {
    console.log(`Match ID: ${this.matchId}`);
    console.log(`Venue: ${this.venue}`);
  }
}