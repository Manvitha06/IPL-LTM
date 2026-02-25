import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginComponent } from '../app/auth/components/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RegistrationComponent } from '../app/auth/components/registration/registration.component';
import { TeamCreateComponent } from '../app/ipl/components/teamcreate/teamcreate.component';
import { CricketerCreateComponent } from '../app/ipl/components/cricketercreate/cricketercreate.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [ReactiveFormsModule]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should show error for invalid username', () => {
    component.loginForm.setValue({
      username: 'invalid@username',
      password: 'Password123'
    });
    component.onSubmit();
    expect(component.errorMessage).toBe('Please fill out all required fields correctly.');
  });

  it('should show backend error for invalid login credentials', () => {
    component.loginForm.setValue({
      username: 'errorUser',
      password: 'Password123'
    });
    component.onSubmit();
    expect(component.errorMessage).toBe('Invalid username or password.');
  });
});

describe('RegistrationComponent', () => {
    let component: RegistrationComponent;
    let fixture: ComponentFixture<RegistrationComponent>;
  
    beforeEach(async () => {
      await TestBed.configureTestingModule({
        declarations: [RegistrationComponent],
        imports: [ReactiveFormsModule]
      }).compileComponents();
    });
  
    beforeEach(() => {
      fixture = TestBed.createComponent(RegistrationComponent);  
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  
    it('should create the component', () => {
      expect(component).toBeTruthy();
    });
  
    it('should show error for invalid email', () => {
      component.registrationForm.setValue({
        fullName: 'John Doe',
        username: 'johnDoe',
        email: 'invalidEmail',
        password: 'Password123'
      });
      component.onSubmit();
      expect(component.errorMessage).toBe('Please fill out all required fields correctly.');
    });
  
    it('should submit form successfully with valid data', () => {
      component.registrationForm.setValue({
        fullName: 'John Doe',
        username: 'johnDoe',
        email: 'john@example.com',
        password: 'Password123'
      });
      component.onSubmit();
      expect(component.successMessage).toBe('Registration successful!');
    });
  });

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

  it('should require teamId to be not null', () => {
    component.teamForm.get('teamId')?.setValue(null);
    component.teamForm.get('teamId')?.markAsTouched();
    fixture.detectChanges();
    expect(component.teamForm.get('teamId')?.invalid).toBeTrue();
  });

  it('should validate teamName for special characters', () => {
    component.teamForm.get('teamName')?.setValue('Team@123');
    component.teamForm.get('teamName')?.markAsTouched();
    fixture.detectChanges();
    expect(component.teamForm.get('teamName')?.invalid).toBeTrue();
  });
});

describe('CricketerCreateComponent', () => {
  let component: CricketerCreateComponent;
  let fixture: ComponentFixture<CricketerCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CricketerCreateComponent],
      imports: [ReactiveFormsModule]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CricketerCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should require experience to be non-negative', () => {
    component.cricketerForm.get('experience')?.setValue(-1);
    component.cricketerForm.get('experience')?.markAsTouched();
    fixture.detectChanges();
    expect(component.cricketerForm.get('experience')?.invalid).toBeTrue();
  });
});

