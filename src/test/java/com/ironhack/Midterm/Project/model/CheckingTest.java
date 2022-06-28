package com.ironhack.Midterm.Project.model;

//@SpringBootTest
//class CheckingTest {
//    @Autowired
//    AccountRepository accountRepository;
//    @Autowired
//    UserRepository userRepository;
//    Checking checking, checking2;
//    User user1, user2;
//    Address address;
//
//    @BeforeEach
//    void setUp() {
//        address = new Address(1, "Ambasaguas 55", 48891);
//        user1 = new Admin(1, "Julen Telleria");
//        user2 = new AccountHolder(2,"Julen Telleria", Date.valueOf("1991-12-12"), address);
//        checking = new Checking(1, BigDecimal.valueOf(2000000), "fngmhg_fhª", user1, Date.valueOf("2018-01-23"));
//        checking2 = new Checking(2, BigDecimal.valueOf(245000), "fzhgnhª", user2, Date.valueOf("2016-12-23"));
//        userRepository.saveAll(List.of(user1,user2));
//        accountRepository.saveAll(List.of(checking, checking2));
//    }
//
//    @AfterEach
//    void tearDown() {
//        accountRepository.deleteAll();
//        userRepository.deleteAll();
//    }
//
//    @Test
//    void setBalance_GoodBalance_Balance() {
//        assertEquals(BigDecimal.valueOf(2000000), checking.getBalance());
//        assertEquals("The balance has been set", checking.setBalance(BigDecimal.valueOf(1244657)));
//    }
//    @Test
//    void setBalance_Balance_Penalty() {
//        assertEquals("A penalty has been taken from the balance", checking.setBalance(BigDecimal.valueOf(150)));
//        checking.setBalance(BigDecimal.valueOf(150));
//        assertEquals(BigDecimal.valueOf(110), checking.getBalance());
//    }
//}