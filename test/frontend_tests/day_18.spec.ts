import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { CricketerArrayComponent } from '../app/ipl/components/cricketerarray/cricketerarray.component';
import { TeamCreateComponent } from '../app/ipl/components/teamcreate/teamcreate.component';

describe('TeamCreateComponent', () => {
  let component: TeamCreateComponent;
  let fixture: ComponentFixture<TeamCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TeamCreateComponent],
      imports: [ReactiveFormsModule]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should show success message when form is valid and submitted', () => {
    component.teamForm.setValue({
      teamId: 1,
      teamName: 'Mumbai Indians',
      location: 'Mumbai',
      ownerName: 'Mukesh Ambani',
      establishmentYear: 2008
    });

    component.onSubmit();
    fixture.detectChanges();

    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.success').textContent).toContain('Team has been successfully created!');
  });

  it('should show error message when form is invalid and submitted', () => {
    component.teamForm.setValue({
      teamId: null,
      teamName: '',
      location: '',
      ownerName: '',
      establishmentYear: null
    });

    component.onSubmit();
    fixture.detectChanges();

    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.error').textContent).toContain('Please fill out all required fields correctly.');
  });

  it('should reset the form when reset button is clicked', () => {
    const resetButton = fixture.nativeElement.querySelector('button[type="button"]');
    resetButton.click();
    
    expect(component.teamForm.get('teamId')?.value).toBeNull();
    expect(component.teamForm.get('teamName')?.value).toBe('');
    expect(component.teamForm.get('location')?.value).toBe('');
    expect(component.teamForm.get('ownerName')?.value).toBe('');
    expect(component.teamForm.get('establishmentYear')?.value).toBe(new Date().getFullYear());
    });
});

describe('CricketerArrayComponent', () => {
    let component: CricketerArrayComponent;
    let fixture: ComponentFixture<CricketerArrayComponent>;
  
    beforeEach(async () => {
      await TestBed.configureTestingModule({
        declarations: [CricketerArrayComponent]
      }).compileComponents();
    });
  
    beforeEach(() => {
      fixture = TestBed.createComponent(CricketerArrayComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  
    it('should create the component', () => {
      expect(component).toBeTruthy();
    });
  
    it('should initialize cricketers array with correct data', () => {
      expect(component.cricketers.length).toBe(4);
      expect(component.cricketers[0].cricketerName).toBe('Virat Kohli');
      expect(component.cricketers[1].cricketerName).toBe('AB de Villiers');
    });

  });