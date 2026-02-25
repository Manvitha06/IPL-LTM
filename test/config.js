const noOfDay = 25;

const testSuites = [
    {
        day: 1,
        dayWiseTestcase: [
            {
                fileName: [
                    "DayTwoTest.java"
                ],
                testcases: [
                   ""
                ]
            }
        ]
    },
    {
        day: 2,
        dayWiseTestcase: [
            {
                fileName: [
                    "DayTwoTest.java"
                ],
                testcases: [
    {
      "name": "testAddTeamToArrayList_Day2",
      "value": "test Add Team to ArrayList Day2"
    },
    {
      "name": "testGetAllTeamsSortedByNameFromArrayList_Day2",
      "value": "test Get All Teams Sorted By Name from ArrayList Day2"
    },
    {
      "name": "testGetAllTeams_Day2",
      "value": "test Get All Teams Day2"
    },
    {
      "name": "testGetAllCricketersSortedByExperience_Day2",
      "value": "test Get All Cricketers Sorted By Experience Day2"
    },
    {
      "name": "testAddCricketerToArrayList_Day2",
      "value": "test Add Cricketer to ArrayList Day2"
    }
  ]

            }
        ]
    },
    {
        day: 3,
        dayWiseTestcase: [
            {
                fileName: [
                    "DayThreeTest.java"
                ],
                testcases: [
    { "name": "testGetAllTeams_Day3", "value": "test Get All Teams Day3" },
    { "name": "testAddTeam_Day3", "value": "test Add Team Day3" },
    { "name": "testUpdateTeam_Day3", "value": "test Update Team Day3" },
    { "name": "testDeleteTeam_Day3", "value": "test Delete Team Day3" },
    { "name": "testGetAllTeamsSortedByNameFromArrayList_Day3", "value": "test Get All Teams Sorted By Name Day3" },
    { "name": "testAddCricketer_Day3", "value": "test Add Cricketer Day3" },
    { "name": "testUpdateCricketer_Day3", "value": "test Update Cricketer Day3" },
    { "name": "testDeleteCricketer_Day3", "value": "test Delete Cricketer Day3" },
    { "name": "testGetAllCricketers_Day3", "value": "test Get All Cricketers Day3" },
    { "name": "testGetAllCricketersSortedByExperience_Day3", "value": "test Get All Cricketers Sorted By Experience Day3" },
    { "name": "testGetAllMatches_Day3", "value": "test Get All Matches Day3" },
    { "name": "testGetMatchById_Day3", "value": "test Get Match By Id Day3" },
    { "name": "testUpdateMatch_Day3", "value": "test Update Match Day3" },
    { "name": "testDeleteMatch_Day3", "value": "test Delete Match Day3" }
  ]

            }
        ]
    },
     {
        day: 4,
        dayWiseTestcase: [
            {
                fileName: [
                    "DayFourTest.java"
                ],
                testcases: [
                    {
      "name": "testMainMethodOutput_Day4",
      "value": "Test Spring Boot main method starts application context successfully Day4"
    }
                ]
            }
        ]
    },
     {
        day: 5,
        dayWiseTestcase: [
            {
                fileName: [
                    "DayFiveTest.java"
                ],
     testcases: [
                  {
      "name": "testAddTeamToArrayList_Day5",
      "value": "Test adding a team to ArrayList via /team/toArrayList endpoint (mockMvc)- Day5"
    },
    {
      "name": "testGetAllTeamsSortedByNameFromArrayList_Day5",
      "value": "Test fetching all teams sorted by name from ArrayList via /team/fromArrayList/sorted endpoint -Day5"
    },
    {
      "name": "testGetAllTeam_Day5",
      "value": "Test fetching all teams from ArrayList via /team/fromArrayList endpoint - Day5"
    }
                ]
            }
        ]
    },
     {
        day: 6,
        dayWiseTestcase: [
            {
                fileName: [
                    "DaySixTest.java"
                ],
                testcases: [
                    {
      "name": "testAddTeamController_Day6",
      "value": "Add a team via /team endpoint and verify repository - Day6"
    },
    {
      "name": "testUpdateTeamController_Day6",
      "value": "Update a team via /team/{id} endpoint and verify name Day6"
    },
    {
      "name": "testDeleteTeamController_Day6",
      "value": "Delete a team via /team/{id} endpoint - Day6"
    },
    {
      "name": "testGetAllTeam_Day6",
      "value": "Fetch all teams from service - Day6"
    },
    {
      "name": "testGetTeamById_Day6",
      "value": "Fetch team by ID from service - Day6"
    }
                ]
            }
        ]
    },
     {
        day: 7,
        dayWiseTestcase: [
            {
                fileName: [
                    "DaySevenTest.java"
                ],
                testcases: [
                    {
      "name": "testGetAllCricketer_Day7",
      "value": "Fetch all cricketers from service - Day7 "
    },
    {
      "name": "testGetCricketersByTeam_Day7",
      "value": "Fetch cricketers of a specific team - Day7"
    },
    {
      "name": "testGetAllCricketersSortedByExperience_Day7",
      "value": "Fetch cricketers sorted by experience - Day7"
    },
    {
      "name": "testUpdateCricketerController_Day7",
      "value": "Update a cricketer via /cricketer/{id} - Day7"
    },
    {
      "name": "testDeleteCricketerController_Day7",
      "value": "Delete a cricketer via /cricketer/{id} - Day7"
    }
                ]
            }
        ]
    },
     {
        day: 8,
        dayWiseTestcase: [
            {
                fileName: [
                    "DayEightTest.java"
                ],
                testcases: [
                    {
      "name": "testGetAllMatchesController_Day8",
      "value": "Fetch all matches via /match endpoint - Day8"
    },
    {
      "name": "testGetAllMatchesByStatusController_Day8",
      "value": "Fetch matches by status via /match/status/{status}- Day8"
    },
    {
      "name": "testUpdateMatch_Day8",
      "value": "Update a match and verify status - Day8"
    },
    {
      "name": "testDeleteMatch_Day8",
      "value": "Delete a match and verify removal - Day8"
    }
                ]
            }
        ]
    },
     {
        day: 9,
        dayWiseTestcase: [
            {
                fileName: [
                    "DayNineTest.java"
                ],
                testcases: [
                    {
      "name": "testTeamAlreadyExistsException_Day9",
      "value": "Test that adding a duplicate team via JPA service throws TeamAlreadyExistsException - Day9 "
    },
    {
      "name": "testTeamDoesNotExistException_Day9",
      "value": "Test that fetching a non-existent team by ID throws TeamDoesNotExistException - Day9"
    },
    {
      "name": "testNoMatchesFoundExceptionException_Day9",
      "value": "Test that fetching matches by status with no matches throws NoMatchesFoundException - Day9"
    }
                ]
            }
        ]
    },
     {
        day: 10,
        dayWiseTestcase: [
            {
                fileName: [
                    "DayTenTest.java"
                ],
                testcases: [
                   {
      "name": "testGetAllVotesController_Day10",
      "value": "Test fetching all votes via /vote endpoint returns correct number of votes - Day10"
    },
    {
      "name": "testCreateVoteController_Day10",
      "value": "Test creating a vote via /vote endpoint successfully stores the vote - Day10"
    },
    {
      "name": "testGetVotesCountByAllCategory_Day10",
      "value": "Test fetching vote counts by category via /vote/count endpoint returns correct counts - Day10"
    }
                ]
            }
        ]
    },
     {
        day: 11,
        dayWiseTestcase: [
            {
                fileName: [
                    "DayElevenTest.java"
                ],
                testcases: [
                    {
      "name": "testGetAllBookings_Day11",
      "value": "Test fetching all ticket bookings via /ticket endpoint returns correct bookings - Day11"
    },
    {
      "name": "testCreateBooking_Day11",
      "value": "Test creating a ticket booking via /ticket endpoint successfully stores the booking- Day11"
    },
    {
      "name": "testCancelBooking_Day11",
      "value": "Test cancelling a ticket booking via /ticket/{id} endpoint successfully deletes the booking - Day11"
    },
    {
      "name": "testGetBookingsByUserEmail_Day11",
      "value": "Test fetching bookings by user email via /ticket/user/{email} endpoint returns correct bookings - Day11"
    }
                ]
            }
        ]
    },
     {
        day: 12,
        dayWiseTestcase: [
            {
                fileName: [
                    "DayTwelveTest.java"
                ],
                testcases: [
                    {
      "name": "testGenerateToken_Day12",
      "value": "Test JWT token generation includes correct username and role claims - Day12"
    },
    {
      "name": "testValidateToken_Day12",
      "value": "Test JWT token validation returns true for valid token and user details - Day12"
    }
                ]
            }
        ]
    },
     {
        day: 13,
        dayWiseTestcase: [
            {
                fileName: [
                    "DayThirteenTest.java"
                ],
                testcases: [
                    {
      "name": "testTeamControllerGetAllTeam_Day13",
      "value": "Test fetching all teams via /team endpoint returns correct team list - Day13"
    },
    {
      "name": "testTeamControllerAddTeam_Day13",
      "value": "Test adding a team via /team endpoint creates team successfully in repository -- Day13"
    },
    {
      "name": "testTeamControllerDeleteTeam_Day13",
      "value": "Test deleting a team via /team/{teamId} endpoint removes team from repository -- Day13"
    },
    {
      "name": "testCricketerControllerGetAllCricketer_Day13",
      "value": "Test fetching all cricketers via /cricketer endpoint returns correct cricketer list- Day13"
    },
    {
      "name": "testCricketerControllerAddCricketer_Day13",
      "value": "Test adding a cricketer via /cricketer endpoint creates cricketer successfully in repository - Day13"
    },
    {
      "name": "testMatchControllerGetAllMatches_Day13",
      "value": "Test fetching all matches via /match endpoint returns correct match list - Day13"
    },
    {
      "name": "testMatchControllerUpdateMatch_Day13",
      "value": "Test updating a match via /match/{id} endpoint successfully modifies match status- Day13"
    },
    {
      "name": "testRegisterUserSuccess_Day13",
      "value": "Test user registration via /user/register endpoint successfully registers and returns user details - Day13"
    }
                ]
            }
        ]
    },
    {
        day: 14,
        dayWiseTestcase: [
            {
                fileName: [
                    "day_14_15.test.js"
                ],
                testcases: [
                   
    {
      "name": "register function should log correct information - Day 14_15",
      "value": "Test register() logs correct name, email, username, and password information from DOM input fields_Day14_15"
    }
                ]
            }
        ]
    },
    {
        day: 15,
        dayWiseTestcase: [
            {
                fileName: [
                    "day_14_15.test.js"
                ],
                testcases: [
                    {
      "name": "login function should log correct information - Day 14_15",
      "value": "Test login() logs correct username and password information from DOM input field_Day 14_15"
    }
    
                ]
            }
        ]
    },
    {
        day: 16,
        dayWiseTestcase: [
            {
                fileName: [
                    "day_16.spec.ts"
                ],
                testcases: [
                  {
      "name": "Cricketer Class - displayInfo method",
      "value": "Test displayInfo() logs correct cricketer details including ID, team ID, and name_Day16"
    },
    {
      "name": "Team Class - displayInfo method",
      "value": "Test displayInfo() logs correct team details including ID, name, and location_Day16"
    },
    {
      "name": "Match Class - displayInfo method",
      "value": "Test displayInfo() logs correct match details including ID and venue_Day16"
    }
                ]
            }
        ]
    },
    {
        day: 17,
        dayWiseTestcase: [
            {
                fileName: [
                    "day_17.spec.ts"
                ],
                testcases: [
                   {
      "name": "CricketerSampleComponent - display cricketer information",
      "value": "Test verifies that the CricketerSampleComponent correctly renders cricketer details such as ID, team ID, and name in the template_Day17"
    },
    {
      "name": "TeamSampleComponent - display team details correctly",
      "value": "Test ensures that the TeamSampleComponent properly displays team details including ID, name, and location in the rendered output_Day17"
    }
                ]
            }
        ]
    },
    {
        day: 18,
        dayWiseTestcase: [
            {
                fileName: [
                    "day_18.spec.ts"
                ],
                testcases: [
                   {
      "name": "TeamCreateComponent - component creation",
      "value": "Verifies that the TeamCreateComponent initializes correctly and is truthy upon creation_Day18"
    },
    {
      "name": "TeamCreateComponent - valid form submission shows success message",
      "value": "Ensures that when valid data is entered and submitted, the success message 'Team has been successfully created!' is displayed_Day18"
    },
    {
      "name": "TeamCreateComponent - invalid form submission shows error message",
      "value": "Checks that when invalid data is submitted, an appropriate error message 'Please fill out all required fields correctly.' is shown_Day18"
    },
    {
      "name": "TeamCreateComponent - reset button functionality",
      "value": "Confirms that clicking the reset button clears form fields and sets the establishmentYear to the current year_Day18"
    },
    {
      "name": "CricketerArrayComponent - component creation",
      "value": "Verifies that the CricketerArrayComponent initializes properly and is truthy_Day18"
    },
    {
      "name": "CricketerArrayComponent - cricketers array initialization",
      "value": "Ensures that the cricketers array is initialized with four predefined objects, starting with 'Virat Kohli' and 'AB de Villiers'_Day18"
    }
                ]
            }
        ]
    },
    {
        day: 19,
        dayWiseTestcase: [
            {
                fileName: [
                    "day_19.spec.ts"
                ],
                testcases: [
                   {
      "name": "CricketerCreateComponent - component creation",
      "value": "Verifies that the CricketerCreateComponent initializes correctly and is truthy upon creation_Day19"
    },
    {
      "name": "CricketerCreateComponent - valid form submission creates cricketer",
      "value": "Ensures that when valid cricketer data is submitted, a cricketer object is created and the success message 'Cricketer created successfully!' is displayed_Day19"
    },
    {
      "name": "CricketerCreateComponent - reset form functionality",
      "value": "Checks that calling resetForm() clears all form fields and sets cricketerId to null_Day19"
    },
    {
      "name": "MatchCreateComponent - component creation",
      "value": "Verifies that the MatchCreateComponent initializes correctly and is truthy upon creation_Day19"
    },
    {
      "name": "MatchCreateComponent - valid form submission creates match",
      "value": "Ensures that when valid match data is submitted, a match object is created and the success message 'Match created successfully!' is displayed_Day19"
    },
    {
      "name": "MatchCreateComponent - reset form functionality",
      "value": "Checks that calling resetForm() clears all form fields and sets matchId to null_Day19"
    }
                ]
            }
        ]
    },
    {
        day: 20,
        dayWiseTestcase: [
            {
                fileName: [
                    "day_20.spec.ts"
                ],
                testcases: [
                   {
      "name": "VoteComponent - component creation",
      "value": "Verifies that the VoteComponent initializes correctly and is truthy upon creation_Day20"
    },
    {
      "name": "VoteComponent - valid form submission",
      "value": "Ensures that when a valid vote form is submitted, a vote object is created and the success message 'Vote submitted successfully!' is displayed_Day20"
    },
    {
      "name": "VoteComponent - invalid form submission shows error",
      "value": "Checks that when the form is invalid, an error message 'Please fill out all required fields correctly.' is displayed_Day20"
    },
    {
      "name": "TicketBookingComponent - component creation",
      "value": "Verifies that the TicketBookingComponent initializes correctly and is truthy upon creation_Day20"
    },
    {
      "name": "TicketBookingComponent - valid form submission",
      "value": "Ensures that when valid ticket booking data is submitted, a ticketBooking object is created and the success message 'Tickets booked successfully!' is displayed_Day20"
    },
    {
      "name": "TicketBookingComponent - invalid form submission shows error",
      "value": "Checks that when the form is invalid, the error message 'Please fill out all required fields correctly.' is displayed_Day20"
    }
                ]
            }
        ]
    },
    {
        day: 21,
        dayWiseTestcase: [
            {
                fileName: [
                    "day_21.spec.ts"
                ],
                testcases: [
                   {
      "name": "LoginComponent - component creation",
      "value": "Verifies that the LoginComponent initializes correctly and is truthy upon creation_Day21"
    },
    {
      "name": "LoginComponent - invalid username validation",
      "value": "Ensures an error message 'Please fill out all required fields correctly.' is shown when username format is invalid_Day21"
    },
    {
      "name": "LoginComponent - invalid credentials backend error",
      "value": "Checks that when invalid login credentials are entered, the error message 'Invalid username or password.' is displayed_Day21"
    },
    {
      "name": "RegistrationComponent - component creation",
      "value": "Verifies that the RegistrationComponent initializes correctly and is truthy upon creation_Day21"
    },
    {
      "name": "RegistrationComponent - invalid email validation",
      "value": "Ensures that if the email format is invalid, the error message 'Please fill out all required fields correctly.' is displayed_Day21"
    },
    {
      "name": "RegistrationComponent - valid form submission success",
      "value": "Checks that when valid registration data is submitted, the success message 'Registration successful!' is displayed_Day21"
    },
    {
      "name": "TeamCreateComponent - component creation",
      "value": "Verifies that the TeamCreateComponent initializes correctly and is truthy upon creation_Day21"
    },
    {
      "name": "TeamCreateComponent - teamId required validation",
      "value": "Ensures that the teamId field must not be null and validation error occurs if left empty_Day21"
    },
    {
      "name": "TeamCreateComponent - teamName special character validation",
      "value": "Checks that teamName field does not allow special characters like '@' and marks field invalid_Day21"
    },
    {
      "name": "CricketerCreateComponent - component creation",
      "value": "Verifies that the CricketerCreateComponent initializes correctly and is truthy upon creation_Day21"
    },
    {
      "name": "CricketerCreateComponent - experience non-negative validation",
      "value": "Ensures that the experience field cannot have negative values and validation marks it as invalid_Day21"
    }
                ]
            }
        ]
    },
     {
        day: 22,
        dayWiseTestcase: [
            {
                fileName: [
                    "day_22.spec.ts"
                ],
                testcases: [
                   {
      "name": "AuthService_creation",
      "value": "Verifies that AuthService is instantiated successfully with HttpClientTestingModule_Day22"
    },
    {
      "name": "AuthService_login_POST",
      "value": "Ensures AuthService.login() makes a POST request to /user/login with correct user payload and returns expected response_Day22"
    },
    {
      "name": "AuthService_getToken_localStorage",
      "value": "Checks that AuthService.getToken() retrieves the token correctly from localStorage_Day22"
    },
    {
      "name": "AuthService_createUser_POST",
      "value": "Verifies AuthService.createUser() makes a POST request to /user/register with correct request body and returns created user data_Day22"
    },
    {
      "name": "LoginComponent_creation",
      "value": "Ensures LoginComponent initializes successfully within the testing module_Day22"
    },
    {
      "name": "LoginComponent_ngOnInit_initialForm",
      "value": "Checks that ngOnInit() initializes loginForm with empty username and password fields and validators_Day22"
    },
    {
      "name": "LoginComponent_onSubmit_invalidForm",
      "value": "Validates that onSubmit() does not call AuthService.login() when the loginForm is invalid_Day22"
    },
    {
      "name": "LoginComponent_onSubmit_validForm",
      "value": "Ensures onSubmit() calls AuthService.login() with valid username and password when form is valid_Day22"
    },
    {
      "name": "RegistrationComponent_creation",
      "value": "Verifies RegistrationComponent is created successfully and compiles correctly_Day22"
    },
    {
      "name": "RegistrationComponent_ngOnInit_initialForm",
      "value": "Checks that ngOnInit() initializes registrationForm with empty username and password fields and validators_Day22"
    }          ]
            }
        ]
    },
     {
        day: 23,
        dayWiseTestcase: [
            {
                fileName: [
                    "day_23.spec.ts"
                ],
                testcases: [
                   {
      "name": "DashboardComponent - load teams, cricketers, and matches",
      "value": "Verifies that DashboardComponent calls IplService.getAllTeams(), getAllCricketers(), and getAllMatches() on initialization and loads the correct data into the component properties_Day23"
    },
    {
      "name": "DashboardComponent - handle empty lists",
      "value": "Checks that DashboardComponent correctly handles empty arrays returned from IplService and sets teams, cricketers, and matches to empty arrays_Day23"
    },
    {
      "name": "TeamCreateComponent - form initialization",
      "value": "Ensures that TeamCreateComponent initializes the teamForm with validators and that all required fields are initially invalid_Day23"
    },
    {
      "name": "TeamCreateComponent - valid form submission",
      "value": "Verifies that submitting a valid teamForm calls IplService.addTeam() and displays the success message 'Team created successfully!'_Day23"
    },
    {
      "name": "TeamCreateComponent - invalid form submission shows error",
      "value": "Checks that submitting an invalid teamForm does not call IplService.addTeam() and displays the error message 'Please fill out all required fields correctly.'_Day23"
    }
                ]
            }
        ]
    },
    {
        day: 24,
        dayWiseTestcase: [
            {
                fileName: [
                    "day_24.spec.ts"
                ],
                testcases: [
                  {
      "name": "TicketBookingComponent - component creation",
      "value": "Verifies that the TicketBookingComponent initializes correctly and is truthy upon creation_Day24"
    },
    {
      "name": "TicketBookingComponent - load matches",
      "value": "Ensures that TicketBookingComponent loads matches correctly using IplService.getAllMatches()_Day24"
    },
    {
      "name": "TicketBookingComponent - valid form submission",
      "value": "Ensures that when a valid ticket booking form is submitted, a booking is created and the success message 'Ticket booked successfully!' is displayed_Day24"
    },
    {
      "name": "TicketBookingComponent - invalid form submission shows error",
      "value": "Checks that submitting an invalid ticket booking form displays the error message 'Please fill out all required fields correctly.'_Day24"
    },
    {
      "name": "DashboardComponent - component creation",
      "value": "Verifies that the DashboardComponent initializes correctly and is truthy upon creation_Day24"
    },
    {
      "name": "DashboardComponent - fetch tickets by email",
      "value": "Ensures that DashboardComponent fetches tickets booked for a given email and populates ticketsBooked correctly_Day24"
    },
    {
      "name": "VoteComponent - component creation",
      "value": "Verifies that the VoteComponent initializes correctly and is truthy upon creation_Day24"
    },
    {
      "name": "VoteComponent - load teams and cricketers",
      "value": "Checks that VoteComponent loads available teams and cricketers from IplService correctly_Day24"
    },
    {
      "name": "VoteComponent - valid form submission",
      "value": "Ensures that casting a valid vote creates a vote object and displays the success message 'Vote casted successfully!'_Day24"
    },
    {
      "name": "VoteComponent - invalid form submission shows error",
      "value": "Checks that submitting an invalid vote form displays the error message 'Please fill out all required fields correctly.'_Day24"
    }
                ]
            }
        ]
    },
    {
        day: 25,
        dayWiseTestcase: [
            {
                fileName: [
                    "day_25.spec.ts"
                ],
                testcases: [
                  {
      "name": "TeamEditComponent - component creation",
      "value": "Verifies that the TeamEditComponent initializes correctly and is truthy upon creation_Day25"
    },
    {
      "name": "TeamEditComponent - form initialization",
      "value": "Checks that the form is created with required controls: teamName, location, ownerName, establishmentYear_Day25"
    },
    {
      "name": "TeamEditComponent - load team details on init",
      "value": "Ensures that getTeamById() is called and team details are loaded into the form correctly_Day25"
    },
    {
      "name": "TeamEditComponent - valid form submission updates team",
      "value": "Verifies that submitting a valid form calls updateTeam() with correct team data_Day25"
    },
    {
      "name": "TeamEditComponent - invalid form submission does not update team",
      "value": "Ensures that submitting an invalid form does not call updateTeam()_Day25"
    },
    {
      "name": "DashboardComponent - component creation",
      "value": "Verifies that the DashboardComponent initializes correctly and is truthy upon creation_Day25"
    },
    {
      "name": "DashboardComponent - delete team functionality",
      "value": "Ensures that clicking the delete button calls deleteTeam() and confirms deletion using window.confirm_Day25"
    }
                ]
            }
        ]
    }
]

module.exports = {
    noOfDay,
    testSuites
};
