import { Cricketer } from '../app/ipl/types/Cricketer';
import { Team } from '../app/ipl/types/Team';
import { Match } from '../app/ipl/types/Match';

describe('Classes', () => {
  
  describe('Cricketer Class', () => {
    let cricketer: Cricketer;
    let spy: jasmine.Spy;

    beforeEach(() => {
      cricketer = new Cricketer(1, 1, "Virat", 32, "Indian", 14, "Batsman", 580, 50);
      spy = spyOn(console, 'log');
    });

    it('displayInfo method should log correct information', () => {
      cricketer.displayInfo();
      expect(spy.calls.argsFor(0)).toEqual(['Cricketer ID: 1']);
      expect(spy.calls.argsFor(1)).toEqual(['Team ID: 1']);
      expect(spy.calls.argsFor(2)).toEqual(['Cricketer Name: Virat']);
    });
  });

  describe('Team Class', () => {
    let team: Team;
    let spy: jasmine.Spy;

    beforeEach(() => {
      team = new Team(1, "CSK", "Chennai", "Dhoni", 2015);
      spy = spyOn(console, 'log');
    });

    it('displayInfo method should log correct information', () => {
      team.displayInfo();
      expect(spy).toHaveBeenCalledWith(jasmine.stringMatching(/Team\s*ID\s*:\s*1/));
      expect(spy).toHaveBeenCalledWith(jasmine.stringMatching(/Team\s*Name\s*:\s*CSK/));
      expect(spy).toHaveBeenCalledWith(jasmine.stringMatching(/Location\s*:\s*Chennai/));
    });
  });

  describe('Match Class', () => {
    let match: Match;
    let spy: jasmine.Spy;

    beforeEach(() => {
      match = new Match(1, 1, 2, new Date('2024-10-15'),'Kolkata', 'Team 1 won by 5 points', 'Completed',1);
      spy = spyOn(console, 'log');
    });

    it('displayInfo method should log correct information', () => {
      match.displayInfo();
      expect(spy).toHaveBeenCalledWith(jasmine.stringMatching(/Match\s*ID\s*:\s*1/));
      expect(spy).toHaveBeenCalledWith(jasmine.stringMatching(/Venue\s*:\s*Kolkata/));
    });
  });

});
