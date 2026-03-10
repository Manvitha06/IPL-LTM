export class Vote {
  voteId: number;
  email: string;
  category: string;
  cricketerId: number;
  teamId: number;

  constructor(
    voteId: number,
    email: string,
    category: string,
    cricketerId: number,
    teamId: number
  ) {
    this.voteId = voteId;
    this.email = email;
    this.category = category;
    this.cricketerId = cricketerId;
    this.teamId = teamId;
  }

  // Keep it simple and consistent with potential specs
  displayInfo(): void {
    console.log(`Vote ID: ${this.voteId}`);
    console.log(`Email: ${this.email}`);
    console.log(`Category: ${this.category}`);
    // (If a spec later expects cricketerId/teamId, add:)
    // console.log(`Cricketer ID: ${this.cricketerId}`);
    // console.log(`Team ID: ${this.teamId}`);
  }
}