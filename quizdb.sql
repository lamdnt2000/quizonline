USE [master]
GO
/****** Object:  Database [quizdb]    Script Date: 12/8/2021 6:58:16 PM ******/
CREATE DATABASE [quizdb]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'quizdb', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\quizdb.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'quizdb_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\quizdb_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [quizdb] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [quizdb].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [quizdb] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [quizdb] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [quizdb] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [quizdb] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [quizdb] SET ARITHABORT OFF 
GO
ALTER DATABASE [quizdb] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [quizdb] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [quizdb] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [quizdb] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [quizdb] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [quizdb] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [quizdb] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [quizdb] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [quizdb] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [quizdb] SET  DISABLE_BROKER 
GO
ALTER DATABASE [quizdb] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [quizdb] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [quizdb] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [quizdb] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [quizdb] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [quizdb] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [quizdb] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [quizdb] SET RECOVERY FULL 
GO
ALTER DATABASE [quizdb] SET  MULTI_USER 
GO
ALTER DATABASE [quizdb] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [quizdb] SET DB_CHAINING OFF 
GO
ALTER DATABASE [quizdb] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [quizdb] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [quizdb] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'quizdb', N'ON'
GO
USE [quizdb]
GO
/****** Object:  Table [dbo].[Choise]    Script Date: 12/8/2021 6:58:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Choise](
	[choice_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[answer] [varchar](255) NULL,
	[answernumber] [int] NULL,
	[quest_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[choice_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Member]    Script Date: 12/8/2021 6:58:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Member](
	[email] [varchar](255) NOT NULL,
	[fullname] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[status] [varchar](255) NULL,
	[role_Id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Question]    Script Date: 12/8/2021 6:58:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Question](
	[quest_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[correctAnswer] [int] NULL,
	[dateCreate] [datetime2](7) NULL,
	[dateUpdate] [datetime2](7) NULL,
	[questionTitle] [varchar](255) NULL,
	[status] [int] NULL,
	[userCreate] [varchar](255) NULL,
	[userUpdate] [varchar](255) NULL,
	[subject_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[quest_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Quiz]    Script Date: 12/8/2021 6:58:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Quiz](
	[quiz_Id] [bigint] IDENTITY(1,1) NOT NULL,
	[dateCreate] [datetime2](7) NULL,
	[dateSubmit] [datetime2](7) NULL,
	[quizTime] [bigint] NULL,
	[status] [int] NULL,
	[total] [float] NULL,
	[email] [varchar](255) NULL,
	[exam_Id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[quiz_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[QuizCategory]    Script Date: 12/8/2021 6:58:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[QuizCategory](
	[exam_id] [bigint] IDENTITY(1,1) NOT NULL,
	[examName] [varchar](255) NULL,
	[examTime] [bigint] NULL,
	[numQuest] [int] NULL,
	[timeCreate] [datetime2](7) NULL,
	[subject_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[exam_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[QuizQuestionList]    Script Date: 12/8/2021 6:58:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuizQuestionList](
	[quest_id] [bigint] NOT NULL,
	[quiz_id] [bigint] NOT NULL,
	[memResult] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[quest_id] ASC,
	[quiz_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Role]    Script Date: 12/8/2021 6:58:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Role](
	[role_Id] [bigint] IDENTITY(1,1) NOT NULL,
	[roleName] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[role_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Subject]    Script Date: 12/8/2021 6:58:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Subject](
	[subject_Id] [bigint] IDENTITY(1,1) NOT NULL,
	[dateCreate] [datetime2](7) NULL,
	[subjectName] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[subject_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Choise] ON 

INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (1, N' Pipeline', 1, 1)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (2, N'Superscalar', 2, 1)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (3, N'Multicore', 3, 1)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (4, N'None of the other choices', 4, 1)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (5, N'Processor, Registers, I/O Modules, Main Memory', 1, 2)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (6, N'Processor, Registers, Main Memory, System Bus', 2, 2)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (7, N'Processor, Main Memory, I/O Modules, System Bus', 3, 2)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (8, N'None of the other choices', 4, 2)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (9, N'Disable all interrupts', 1, 3)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (10, N'Read the time-of-day clock', 2, 3)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (11, N'Set the time-of-day clock', 3, 3)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (12, N'Change the memory map', 4, 3)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (13, N' Increasing cost per bit', 1, 4)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (14, N'Decreasing capacity', 2, 4)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (15, N'Increasing access time', 3, 4)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (16, N'None of the other choices', 4, 4)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (17, N'PSW', 1, 5)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (18, N'PSW and PC', 2, 5)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (19, N'PSW and Contents of processor registers', 3, 5)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (20, N'None of the other choices', 4, 5)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (21, N'Heap sort', 1, 6)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (22, N'Radix sort', 2, 6)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (23, N'Bubble sort', 3, 6)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (24, N'Quick sort', 4, 6)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (25, N'Radix transformation', 1, 7)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (26, N'Extraction', 2, 7)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (27, N'Mid-square function', 3, 7)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (28, N'Folding', 4, 7)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (29, N'Iteration statements', 1, 8)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (30, N'Stacks', 2, 8)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (31, N'Linked List', 3, 8)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (32, N'All of the others.', 4, 8)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (33, N'22', 1, 9)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (34, N'15', 2, 9)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (35, N'9', 3, 9)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (36, N'32', 4, 9)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (37, N'16 13 11 12 3 7 4 2 9', 1, 10)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (38, N'16 13 12 11 9 7 4 3 2', 2, 10)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (39, N'16 12 13 4 9 7 11 2 3', 3, 10)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (40, N'16 12 11 9 13 7 4 3 2', 4, 10)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (41, N'The most important tests first', 1, 11)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (42, N'The most difficult tests first(to allow maximum time for fixing)', 2, 11)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (43, N'The easiest tests first (to give initial confidence)', 3, 11)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (44, N'The order they are thought of', 4, 11)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (45, N'How well you know a particular technique', 1, 12)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (46, N'The objective of the test', 2, 12)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (47, N'How appropriate the technique is for testing the application', 3, 12)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (48, N'Whether there is a tool to support the technique', 4, 12)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (49, N' Should be able to understand a functional specification or requirements document', 1, 13)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (50, N'Should be able to understand the source code.', 2, 13)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (51, N'Is highly motivated to find faults', 3, 13)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (52, N'Is creative to find the systems weaknesses', 4, 13)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (53, N'The test manager should do only automated regression testing.', 1, 14)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (54, N'The test manager is justified in her decision because no bug has been fixed in other modules', 2, 14)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (55, N'The test manager should only do confirmation testing. There is no need to do regression testing', 3, 14)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (56, N'Regression testing should be done on other modules as well because fixing one module may affect other modules', 4, 14)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (57, N'A goal is that no more failures will result from the remaining defects', 1, 15)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (58, N' A goal is to find as many failures as possible so that the cause of the failures can be identified and fixed', 2, 15)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (59, N'A goal is to eliminate as much as possible the causes of defects', 3, 15)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (60, N'A goal is to fulfil all requirements for testing that are defined in the project plan. B  Q. 1009: Which set of metrics', 4, 15)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (61, N'2', 1, 16)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (62, N'3', 2, 16)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (63, N'4', 3, 16)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (64, N'5', 4, 16)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (65, N'5', 1, 17)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (66, N'4', 2, 17)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (67, N'6', 3, 17)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (68, N'7', 4, 17)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (69, N'10', 1, 18)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (70, N'8', 2, 18)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (71, N'9', 3, 18)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (72, N'7', 4, 18)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (73, N'8', 1, 19)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (74, N'7', 2, 19)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (75, N'9', 3, 19)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (76, N'22', 4, 19)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (77, N'2', 1, 20)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (78, N'11', 2, 20)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (79, N'12', 3, 20)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (80, N'67', 4, 20)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (81, N'3', 1, 21)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (82, N'2', 2, 21)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (83, N'11', 3, 21)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (84, N'4', 4, 21)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (85, N'1', 1, 22)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (86, N'12', 2, 22)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (87, N'4', 3, 22)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (88, N'3', 4, 22)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (89, N'12', 1, 23)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (90, N'11', 2, 23)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (91, N'33', 3, 23)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (92, N'44', 4, 23)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (93, N'1', 1, 24)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (94, N'12', 2, 24)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (95, N'19', 3, 24)
INSERT [dbo].[Choise] ([choice_ID], [answer], [answernumber], [quest_id]) VALUES (96, N'18', 4, 24)
SET IDENTITY_INSERT [dbo].[Choise] OFF
INSERT [dbo].[Member] ([email], [fullname], [password], [status], [role_Id]) VALUES (N'DuyHV@gmail.com', N'Ho Vinh Duy', N'$2a$10$H0NKSY0IDKySIig08emeVe5.wbsajmqI/C541ixfIFTxbdaymWkHO', NULL, 1)
INSERT [dbo].[Member] ([email], [fullname], [password], [status], [role_Id]) VALUES (N'lam@gmail.com', N'Lam Do', N'$2a$10$4GKdEdsSq5nCtwkF5J6hu.Nermbi2t9cLNSDW3HzMB2JZJsdxitp6', NULL, 2)
SET IDENTITY_INSERT [dbo].[Question] ON 

INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (1, 2, CAST(N'2021-12-08 17:58:06.0670000' AS DateTime2), NULL, N'A CPU may have multiple execution units, so that can carry out multiple instructions in the same time is called:', 1, NULL, NULL, 1)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (2, 3, CAST(N'2021-12-08 17:59:04.2800000' AS DateTime2), NULL, N'The four main structural elements of a computer system are:', 1, NULL, NULL, 1)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (3, 2, CAST(N'2021-12-08 18:00:28.5310000' AS DateTime2), NULL, N'Which of the following instructions should be allowed in user mode?', 1, NULL, NULL, 1)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (4, 3, CAST(N'2021-12-08 18:01:29.0470000' AS DateTime2), NULL, N'As one proceeds down the memory hierarchy(from inboard memory to offline storage), which of the following conditions is correct?', 1, NULL, NULL, 1)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (5, 2, CAST(N'2021-12-08 18:02:10.0710000' AS DateTime2), NULL, N'Information that must be saved prior to the processor transferring control to the interrupt handler routine includes:', 1, NULL, NULL, 1)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (6, 4, CAST(N'2021-12-08 18:03:42.4720000' AS DateTime2), NULL, N' Which of the following Sorting algorithms use Divide and Conquer strategy?', 1, NULL, NULL, 2)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (7, 2, CAST(N'2021-12-08 18:04:32.2870000' AS DateTime2), NULL, N'What type of the hash functions that can be used in an English - Vietnamese dictionary?', 1, NULL, NULL, 2)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (8, 1, CAST(N'2021-12-08 18:05:35.4420000' AS DateTime2), NULL, N'A recursive method may be eliminated by using .................', 1, NULL, NULL, 2)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (9, 2, CAST(N'2021-12-08 18:06:42.5780000' AS DateTime2), NULL, N'How many recursive calls to calculate the 5th Fibonacci number?', 1, NULL, NULL, 2)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (10, 1, CAST(N'2021-12-08 18:07:47.9250000' AS DateTime2), NULL, N'An array contains the elements shown below. What would be the order of the elements in the array after phase 1 of the heap sort algorithm? 3 9 7 2 11 16 4 13 12', 1, NULL, NULL, 2)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (11, 1, CAST(N'2021-12-08 18:10:08.0610000' AS DateTime2), NULL, N'In which order should tests be run?', 1, NULL, NULL, 3)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (12, 2, CAST(N'2021-12-08 18:11:09.9140000' AS DateTime2), NULL, N'What is the important criterion in deciding what testing technique to use?', 1, NULL, NULL, 3)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (13, 2, CAST(N'2021-12-08 18:12:00.4720000' AS DateTime2), NULL, N'Which is not true-The black box tester', 1, NULL, NULL, 3)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (14, 4, CAST(N'2021-12-08 18:12:50.2510000' AS DateTime2), NULL, N'A number of critical bugs are fixed in software. All the bugs are in one module, related to reports. The test manager decides to do regression testing only on the reports module.', 1, NULL, NULL, 3)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (15, 2, CAST(N'2021-12-08 18:13:30.1600000' AS DateTime2), NULL, N'Which of the following statements contains a valid goal for a functional test set?', 1, NULL, NULL, 3)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (16, 1, CAST(N'2021-12-08 18:45:20.5240000' AS DateTime2), NULL, N'1+1 = ', 1, NULL, NULL, 4)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (17, 2, CAST(N'2021-12-08 18:45:39.9010000' AS DateTime2), NULL, N'1 + 3 =', 1, NULL, NULL, 4)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (18, 4, CAST(N'2021-12-08 18:45:59.4860000' AS DateTime2), NULL, N'1 + 6 =', 1, NULL, NULL, 4)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (19, 2, CAST(N'2021-12-08 18:46:15.1160000' AS DateTime2), CAST(N'2021-12-08 18:46:54.4590000' AS DateTime2), N'2 + 5 = ', 1, NULL, NULL, 4)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (20, 2, CAST(N'2021-12-08 18:46:35.7550000' AS DateTime2), NULL, N'5 + 6 =', 1, NULL, NULL, 4)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (21, 3, CAST(N'2021-12-08 18:47:07.4300000' AS DateTime2), CAST(N'2021-12-08 18:47:42.4210000' AS DateTime2), N'4+7 = ', 1, NULL, NULL, 4)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (22, 2, CAST(N'2021-12-08 18:47:18.5090000' AS DateTime2), CAST(N'2021-12-08 18:47:37.9010000' AS DateTime2), N'8+4 = ', 1, NULL, NULL, 4)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (23, 1, CAST(N'2021-12-08 18:47:33.4870000' AS DateTime2), CAST(N'2021-12-08 18:50:14.1360000' AS DateTime2), N'7 + 15', 1, NULL, NULL, 4)
INSERT [dbo].[Question] ([quest_ID], [correctAnswer], [dateCreate], [dateUpdate], [questionTitle], [status], [userCreate], [userUpdate], [subject_id]) VALUES (24, 4, CAST(N'2021-12-08 18:48:09.8760000' AS DateTime2), CAST(N'2021-12-08 18:50:03.0390000' AS DateTime2), N'9+9 = ', 1, NULL, NULL, 4)
SET IDENTITY_INSERT [dbo].[Question] OFF
SET IDENTITY_INSERT [dbo].[Quiz] ON 

INSERT [dbo].[Quiz] ([quiz_Id], [dateCreate], [dateSubmit], [quizTime], [status], [total], [email], [exam_Id]) VALUES (1, CAST(N'2021-12-08 18:49:15.9200000' AS DateTime2), CAST(N'2021-12-08 18:49:33.9480000' AS DateTime2), 4, 1, 2, N'lam@gmail.com', 8)
INSERT [dbo].[Quiz] ([quiz_Id], [dateCreate], [dateSubmit], [quizTime], [status], [total], [email], [exam_Id]) VALUES (2, CAST(N'2021-12-08 18:52:00.0680000' AS DateTime2), CAST(N'2021-12-08 18:52:20.4200000' AS DateTime2), 5, 1, 6, N'lam@gmail.com', 7)
SET IDENTITY_INSERT [dbo].[Quiz] OFF
SET IDENTITY_INSERT [dbo].[QuizCategory] ON 

INSERT [dbo].[QuizCategory] ([exam_id], [examName], [examTime], [numQuest], [timeCreate], [subject_id]) VALUES (1, N'Quiz1', 3, 4, CAST(N'2021-12-08 18:14:09.2310000' AS DateTime2), 1)
INSERT [dbo].[QuizCategory] ([exam_id], [examName], [examTime], [numQuest], [timeCreate], [subject_id]) VALUES (2, N'Quiz2', 3, 4, CAST(N'2021-12-08 18:14:19.6960000' AS DateTime2), 1)
INSERT [dbo].[QuizCategory] ([exam_id], [examName], [examTime], [numQuest], [timeCreate], [subject_id]) VALUES (3, N'Quiz1', 3, 4, CAST(N'2021-12-08 18:14:40.7460000' AS DateTime2), 2)
INSERT [dbo].[QuizCategory] ([exam_id], [examName], [examTime], [numQuest], [timeCreate], [subject_id]) VALUES (4, N'Quiz2', 3, 3, CAST(N'2021-12-08 18:14:53.8410000' AS DateTime2), 2)
INSERT [dbo].[QuizCategory] ([exam_id], [examName], [examTime], [numQuest], [timeCreate], [subject_id]) VALUES (5, N'Quiz1', 3, 5, CAST(N'2021-12-08 18:15:13.9730000' AS DateTime2), 3)
INSERT [dbo].[QuizCategory] ([exam_id], [examName], [examTime], [numQuest], [timeCreate], [subject_id]) VALUES (6, N'Quiz2', 3, 5, CAST(N'2021-12-08 18:15:42.5510000' AS DateTime2), 3)
INSERT [dbo].[QuizCategory] ([exam_id], [examName], [examTime], [numQuest], [timeCreate], [subject_id]) VALUES (7, N'Ez math 1', 5, 8, CAST(N'2021-12-08 18:48:32.9140000' AS DateTime2), 4)
INSERT [dbo].[QuizCategory] ([exam_id], [examName], [examTime], [numQuest], [timeCreate], [subject_id]) VALUES (8, N'Ez math 2', 4, 2, CAST(N'2021-12-08 18:48:46.1900000' AS DateTime2), 4)
SET IDENTITY_INSERT [dbo].[QuizCategory] OFF
INSERT [dbo].[QuizQuestionList] ([quest_id], [quiz_id], [memResult]) VALUES (16, 2, 1)
INSERT [dbo].[QuizQuestionList] ([quest_id], [quiz_id], [memResult]) VALUES (17, 2, 2)
INSERT [dbo].[QuizQuestionList] ([quest_id], [quiz_id], [memResult]) VALUES (18, 2, 4)
INSERT [dbo].[QuizQuestionList] ([quest_id], [quiz_id], [memResult]) VALUES (19, 2, 2)
INSERT [dbo].[QuizQuestionList] ([quest_id], [quiz_id], [memResult]) VALUES (21, 2, 2)
INSERT [dbo].[QuizQuestionList] ([quest_id], [quiz_id], [memResult]) VALUES (22, 2, 3)
INSERT [dbo].[QuizQuestionList] ([quest_id], [quiz_id], [memResult]) VALUES (23, 1, 1)
INSERT [dbo].[QuizQuestionList] ([quest_id], [quiz_id], [memResult]) VALUES (23, 2, 1)
INSERT [dbo].[QuizQuestionList] ([quest_id], [quiz_id], [memResult]) VALUES (24, 1, 4)
INSERT [dbo].[QuizQuestionList] ([quest_id], [quiz_id], [memResult]) VALUES (24, 2, 4)
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([role_Id], [roleName]) VALUES (1, N'Teacher')
INSERT [dbo].[Role] ([role_Id], [roleName]) VALUES (2, N'Student')
SET IDENTITY_INSERT [dbo].[Role] OFF
SET IDENTITY_INSERT [dbo].[Subject] ON 

INSERT [dbo].[Subject] ([subject_Id], [dateCreate], [subjectName]) VALUES (1, CAST(N'2021-12-08 17:56:21.9200000' AS DateTime2), N'OSG')
INSERT [dbo].[Subject] ([subject_Id], [dateCreate], [subjectName]) VALUES (2, CAST(N'2021-12-08 17:56:27.0140000' AS DateTime2), N'CSD')
INSERT [dbo].[Subject] ([subject_Id], [dateCreate], [subjectName]) VALUES (3, CAST(N'2021-12-08 17:56:31.6220000' AS DateTime2), N'SWT')
INSERT [dbo].[Subject] ([subject_Id], [dateCreate], [subjectName]) VALUES (4, CAST(N'2021-12-08 18:45:03.1950000' AS DateTime2), N'Math 101')
SET IDENTITY_INSERT [dbo].[Subject] OFF
ALTER TABLE [dbo].[Choise]  WITH CHECK ADD  CONSTRAINT [FK92urmvn9eukglq1ij2pwar5v6] FOREIGN KEY([quest_id])
REFERENCES [dbo].[Question] ([quest_ID])
GO
ALTER TABLE [dbo].[Choise] CHECK CONSTRAINT [FK92urmvn9eukglq1ij2pwar5v6]
GO
ALTER TABLE [dbo].[Member]  WITH CHECK ADD  CONSTRAINT [FKshjv90ob2hskqmf7t3q2m5vyk] FOREIGN KEY([role_Id])
REFERENCES [dbo].[Role] ([role_Id])
GO
ALTER TABLE [dbo].[Member] CHECK CONSTRAINT [FKshjv90ob2hskqmf7t3q2m5vyk]
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK9gp9c9i6hp9ikei22cv1a3xpm] FOREIGN KEY([subject_id])
REFERENCES [dbo].[Subject] ([subject_Id])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK9gp9c9i6hp9ikei22cv1a3xpm]
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD  CONSTRAINT [FKdeegdp7xen0awxxs3as4yccuy] FOREIGN KEY([exam_Id])
REFERENCES [dbo].[QuizCategory] ([exam_id])
GO
ALTER TABLE [dbo].[Quiz] CHECK CONSTRAINT [FKdeegdp7xen0awxxs3as4yccuy]
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD  CONSTRAINT [FKfxmdcaxdj3eg44vroxlyk5966] FOREIGN KEY([email])
REFERENCES [dbo].[Member] ([email])
GO
ALTER TABLE [dbo].[Quiz] CHECK CONSTRAINT [FKfxmdcaxdj3eg44vroxlyk5966]
GO
ALTER TABLE [dbo].[QuizCategory]  WITH CHECK ADD  CONSTRAINT [FKkrbb6wvdg30rwskraim9gj3cj] FOREIGN KEY([subject_id])
REFERENCES [dbo].[Subject] ([subject_Id])
GO
ALTER TABLE [dbo].[QuizCategory] CHECK CONSTRAINT [FKkrbb6wvdg30rwskraim9gj3cj]
GO
ALTER TABLE [dbo].[QuizQuestionList]  WITH CHECK ADD  CONSTRAINT [FK4r8huky901cn0ewgjc03bk24h] FOREIGN KEY([quiz_id])
REFERENCES [dbo].[Quiz] ([quiz_Id])
GO
ALTER TABLE [dbo].[QuizQuestionList] CHECK CONSTRAINT [FK4r8huky901cn0ewgjc03bk24h]
GO
ALTER TABLE [dbo].[QuizQuestionList]  WITH CHECK ADD  CONSTRAINT [FKtgeu8aor1d9qotw65lgjvhe6n] FOREIGN KEY([quest_id])
REFERENCES [dbo].[Question] ([quest_ID])
GO
ALTER TABLE [dbo].[QuizQuestionList] CHECK CONSTRAINT [FKtgeu8aor1d9qotw65lgjvhe6n]
GO
USE [master]
GO
ALTER DATABASE [quizdb] SET  READ_WRITE 
GO
