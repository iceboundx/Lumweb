-- MySQL dump 10.16  Distrib 10.2.11-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: lumweb
-- ------------------------------------------------------
-- Server version	10.2.11-MariaDB-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` varchar(45) NOT NULL,
  `title` varchar(150) NOT NULL,
  `createTime` datetime NOT NULL,
  `author` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES ('1','Fourth board of directors 17th meeting resolution announcement','2019-02-02 00:00:00','','fina','    LumWeb technology group co., LTD. (the \"company\") will issue the notice of the 17th meeting of the 4th board of directors on January 26, 2019 through a combination of telephone, fax and email.The meeting will be held at 10:00 am on February 1, 2019 in the conference room on the 8th floor of the company, no. 31-37 anling 2nd road, huli district, xiamen by on-site + communication voting, and confirmed by all the directors.This meeting should be attended by 9 directors, 9 of whom will actually attend the meeting. Among them, Ms. Chen shumei, Mr. Zhou hong, Mr. Tang zhiguo and Ms. Chang xiaorong will attend the meeting by means of correspondence.Part of the company\'s supervisors, senior executives attended the meeting.This meeting is presided over by Mr. Zou jianhan, the chairman of the company. The meeting is in accordance with the relevant provisions of the company law of the People\'s Republic of China and the articles of association of the company.\n    i. the meeting deliberated and adopted the proposal that the company meets the conditions for public issuance of convertible corporate bonds by 9 votes, 0 votes against and 0 abstentions.\n    ii. The meeting deliberated and adopted the proposal on the pre-proposal for the public issuance of convertible corporate bonds by 9 votes, 0 votes against and 0 abstenti'),('10','Standardize The Management','2019-05-02 00:00:00','Steven','tech','This is a new era of rapid change, the country and society on the corporate character and responsibility of the new requirements.At the same time, consumer protection awareness is also growing.Conforming to the needs of The Times, standardizing management, adhering to quality and earnestly safeguarding the rights and interests of consumers have become the consensus of Chinese enterprises on the path of innovative development.\nIn 2018, the turbulent changes in the business environment and the twists and turns in the development of enterprises have superimposed resonance, zhongmai technology has gone through a year of trials and hardships.Fortunately, all zhongmai family always work together hand in hand, firmly with the company together to hardship, unremitting forward.I would like to take this opportunity to thank every zhongmai family for their contribution and efforts to the development of the company.<br>\nWhen we are faced with new challenges and pressures, we must think about the needs of the new era and what the consumers really want. Otherwise, we will never know the direction of the future.We must be fully aware that there is still room for improvement in our work and services. We must be mindful of risks and redouble our efforts to make our work and services more real and better. Only by standardizing management, upholding quality and optimizing services can we adapt to the development of The Times and go further.Only do the new era needs the enterprise, do the consumer needs the enterprise, we can find the real sustainable development of the road to success.<br>\nIn order to advocate norms and stick to quality, at the end of 2018, zhongmai science and technology comprehensively launched the development strategy of \"standardized operation, cultural bacon, system operation and beautiful progress\", and launched the \"learning season for all members\" to thoroughly study and implement this development strategy.The construction of \"beautiful midrib\" has become the goal of all midrib people on their new journey in the new era.\"Beauty\" is the need of spiritual quality, \"good\" is the basic needs of everyone\'s survival, the construction of \"beautiful vein\" refers to the external material rich and internal spiritual quality of harmonious development and go hand in hand with the best state.The realization process of this goal is both a challenge and an opportunity, which requires us to readjust and start again with an empty mind.<br>\nIn order to find the best state of career development, to achieve the goal of building a beautiful vein, we must keep the original intention, find the true self, stick to the quality, know how to refuse.The process of retaining original intention and returning to quality is a challenge, which requires us to refuse to drift with the tide, to refuse to associate with the corrupt, and to refuse to get rich by dishonest means.Only by sticking to the beauty of quality can we keep our original intention and the \"truth\", \"kindness\" and \"beauty\" of zhongmai brand, thus laying the foundation stone for the development of enterprises and brands.<br>\nThis is a new era of quality return, quality products, quality service is the new era of enterprise development core competitiveness.The construction of a beautiful mid-vein is not only a growth and transformation in line with the needs of The Times, but also a necessary stage for the success of the mid-vein cause.We must always put the needs of the society and the legitimate rights and interests of consumers in an important position, adhere to the quality of the enterprise, let consumers get quality of life, in order to make zhongmai brand blooming a convincing and convincing charm, so as to win the trust and support of the community and the majority of consumers.<br>\nThis is a new era of honor for quality, we want to operate quality enterprises, do quality products, provide quality services, become quality people.We should build zhongmai business into a business platform to achieve beautiful and charming, and call more people to be quality people. We should take the solution of consumer demand and consumer satisfaction as the starting point of the enterprise, and realize the comprehensive improvement of consumption experience through quality honing.Beauty is the consensus of human civilization that can pass through any religious or ethnic barriers.The more beautiful the practice, the more lasting the success will be.<br>\nAt a new starting point for the future, we must build consensus, pool wisdom and strength, and adhere to the strategy of \"standardized operation, cultural bacon, systematic operations, and beautiful progress\".We must through their own words and deeds, adhere to the standard management, adhere to the quality, in the right direction unswervingly go down;We must be legal standard and customer satisfaction as the real requirements, in the full range of enterprise management and marketing services in the whole process of continuous practice of our common goal - to create a good team culture, have ideal, have a sense of mission, to create a standard management, the quality of the service enterprise, creating a social value has the responsibility to bear the brand in one hundred, for full career growth and your family happiness and social contribution and hard work, dedication, and power.<br>\n2019 is bound to be a year full of opportunities and challenges. It is also a year in which all zhongmai families will win rich returns through standardized work and perfect services.Hope all zhongmai family members continue to be full of confidence, firm belief, active action, their cultivation become a good life communicator, in the process of cultivation, guide more people to know beauty, practice beauty, bloom beauty, create and share quality products and services, write zhongmai new beautiful and brilliant!'),('11','Three years of financial technology','2012-03-04 12:30:21','lijing','fina','The world\'s first adaptive mechanical arm: precise anti-jamming, built by lumweb research and development team\nThe role of mechanical guide dog in the electric future\nBoston power Atlas is perfect for autonomous navigation, this time for real \nTechnology stocks closed, lumweb rose nearly 12%, and is in good condition\nLumweb\'s latest robot factory will be settled in Shanghai and is expected to be completed by the end of 2020!\nLumweb R&D department develops open source human-machine dialogue model ESIM\nLUMweb Artificial Intelligence Interaction Design Institute has published 27 papers\nThe new generation of guide dog speech recognition system is upgraded, and instructions can be executed without wake-up words.'),('2','LumTech get a new tech!','2019-06-02 00:00:00','boss','tech','MADRID - China\'s technology giant Huawei on Friday opened in Madrid its largest flagship store outside China.'),('20190916112603','The Future','2019-09-16 23:26:03','Sue','fina',' LumWeb on September 22, the postscript roadshow \"looking for smart companies\" was held in zhongguancun no.1 park. The activity took \"hard technology\" as the leading direction of the industry and focused on cutting-edge industries such as artificial intelligence and financial technology.Xu Chen, associate publisher of MIT technology review and a founding member of the postscript school, delivered a presentation titled \"how smart companies can harness the future of technology.\"\n<br>\n    \"More and more intelligent hardware will be connected to the artificial intelligence Internet of things, communications Internet of things, we will always encounter privacy issues,\" he said.The simplest privacy problem is that the enterprise will collect a large amount of biological information of users on a terminal. If it is intelligent information, it will collect your voice text. If it is mobile phone face recognition and fingerprint recognition, it will collect your face and fingerprint information.These private data, if enterprises put it in the cloud for processing, then in fact the cloud will have all your private data, it will be in the cloud for private data firewall and cutting, this part is still blank in the law until now.That could be problematic under Europe\'s toughest privacy laws.\"'),('20190916114000','Top priority - better customer service','2019-09-16 23:40:00','Jack','tech','About the customer\'s failure to find baodin software distributors:\nOur company adhering to one - on - one customer software after-sales service.Now, we are worried that our customers can\'t find bodede software. We offer two solutions.\n<br>\nScheme 1: it is suggested that bausch & Germany headquarters strengthen and improve the software after-sales personnel one-to-one file.\nSolution 2: all regional baodai software distributors take over to perfect the files of customers who cannot find the sellers and provide paid after-sales service and perfect the files, so as to truly make customers use baodai software wordless.Ii. Establishment of reservation management platform system.\nWhy does VIP have reservation system?Because the timeliness of this approach is very high, can bring customer satisfaction service experience.\nCan solve some unnecessary problems, such as: some customers will say, everyone is using the doctor DE software, how to first go to his home maintenance software, and not to my house, is not the importance of my house.First of all, as long as it is with our doctor DE software equal treatment, we will put your problems the first time to install personnel to deal with, but the order also have to comply with.\nThree, red envelope system to provide star services.\nNow, some customers will say, why before QQ remote does not charge, now remote how to still charge?Today, my company here to state that, as we all know, employees receive red envelopes are not the same mood.This is a small interaction between the customer and our employees.Nowadays, the country does not advocate giving red envelopes, but a little encouragement, support and recognition for our employees to solve your problems is allowed.\nIv. Management measures of the company\'s archives concerning customers\' Suggestions and feedbacks.\n1. Suggestions from customers.\nOur company will immediately submit to the relevant person in charge to deal with and reply.If an employee fails to respond in time, the incident will be included in the company\'s internal reward and punishment system. We welcome your supervision and correction.\n2. Feedback\nYour comments, our company will set up a file to manage, track, and solve or implement.There are also reasonable explanations and explanations for events that cannot be solved or implemented.\nFive, the company\'s own technical skills learning and assessment.\nAs a technical talent, the basic knowledge of technology is very important.\nMy company will learn the basic knowledge of employees, at the same time, once a month assessment, only the assessment of the personnel can be good for you.Therefore, we will strengthen the number of practice operations to make up for the lack of experience.We also want our customers to understand why we let new employees handle your problems.To solve your problems quickly, we must first solve the staffing problem of technical personnel. We must train new employees.'),('20190924074832','Wang lixin, the company\'s core technician, resigned','2019-09-24 19:48:32','Shanshan Sun','fina','On June 30, 2019, the company received the resignation report submitted by wang lixin, a core technician.<br>\nThe new three board announced on July 3 that the company had received a resignation report from wang lixin, a key technician, on June 30, 2019.<br>\nThe announcement showed that the resignation of the core technical staff do not hold shares in the company.The reason for this resignation is personal.Mr. Wang will step down from his post.<br>\nLumWeb said Mr. Wang, who holds no shares in the company, will not take on any role after his resignation.At present, the company has completed the smooth connection with Mr. Wang lixin\'s work, and his resignation will not have any significant adverse impact on the company\'s daily operation, technical research and development and business development.<br>\nAccording to data from neeq research center, yuexin was listed on neeq on August 10, 2016. Its main business is IT system integration service, value-added sales of IT products and software, maintenance service and high-end value-added service, and proprietary cloud construction service.<br>In 2016, the monthly revenue of new technology was 252 million yuan, up 4.28% year on year.Net profit was 7.484 million yuan, up 384.77 percent year on year.'),('20190924081405','LumWeb completed series B financing of 4.762 billion yuan','2019-09-24 20:14:05','Zi Ji','tech','Lumweb, a fintech startup, has announced a 4.762 billion yuan ($767 million) series B round of financing, co-led by Lightspeed and tiger global management fund, with Morningside Venture Capital, Venture Highway and Y Combinator, a well-known U.S. incubator accelerator.So far, the company has raised $83 million.'),('3','LumTech get a new tech!','2019-02-02 00:00:00','boss','tech','MADRID - China\'s technology giant Huawei on Friday opened in Madrid its largest flagship store outside China.'),('4','LumTech get a new tech!','2019-02-02 00:00:00','boss','tech','MADRID - China\'s technology giant Huawei on Friday opened in Madrid its largest flagship store outside China.'),('5555','LumTech made a breakthrough in robotics','2019-01-01 00:00:00','icebound','tech','LHASA, July 6 (Xinhua) -- Eighty buses using new energy have been put into use Saturday morning in Lhasa, the capital city of China\'s southwest Tibet Autonomous Region, which will help reduce vehicle exhaust emissions and further improve the local air quality.'),('6666','LumTech get a new tech!','2019-02-02 00:00:00','boss','tech','MADRID - China\'s technology giant Huawei on Friday opened in Madrid its largest flagship store outside China.'),('7','LumTech get a new tech!','2019-02-02 00:00:00','boss','tech','MADRID - China\'s technology giant Huawei on Friday opened in Madrid its largest flagship store outside China.'),('8','Change of financial industry model: openness and cooperation','2019-03-02 00:00:00','boss','fina','Under the general trend of digitalization, Internet enterprises and fintech companies take the lead in launching a wave of innovation, launching new payment methods or financial services, and building a new business model, which not only breaks the boundary with traditional finance, but also changes the rules of industry competition.Bank Zero, a disembodied Bank in South Africa, attracted worldwide attention from the start.It does not have a single branch, all its services are provided to users through apps, and is a veritable \"modern app-driven\" bank.Its founder, Jordann, believes that the future is mobile, and that an app-based bank can gain customers\' trust through flexible and secure services.Faced with the \"battle for customers\" of new financial institutions, traditional financial institutions quickly responded, relying on the original brand and user advantages, to build online banking, develop mobile services and continuously innovate financial products. From the beginning, the disrupted financial institutions gradually became the leaders of industry innovation.Nowadays, traditional finance and Internet technology finance are no longer as tense as they used to be. It is either one or the other. Openness, integration and co-construction are becoming the trend of the industry.In 2018, which has been called the first year of China\'s \"open bank\" in the industry, a number of innovative commercial Banks, including SPD bank and China construction bank, have taken the initiative to embrace openness.They work with customers, third-party developers, fintech companies and other partners to build ecosystems based on API technology.New and old financial players give full play to their respective advantages, integrate and embed financial services into various production and life scenarios, and jointly build future-oriented financial services that are \"everywhere\" and \"anytime and anywhere\".Open cooperation and resource sharing bring commercial Banks an open platform economy and opportunities to reconstruct the value chain, and also increase risks, including data leakage risk.It is a great challenge for commercial Banks to control the increasingly complex security pattern while opening up, integrating and accelerating innovation.'),('9','Report: China\'s consumer finance supply is still insufficient','2019-04-02 00:00:00','Jackson','fina',' LumWeb on September 22, the postscript roadshow \"looking for smart companies\" was held in zhongguancun no.1 park. The activity took \"hard technology\" as the leading direction of the industry and focused on cutting-edge industries such as artificial intelligence and financial technology.Xu Chen, associate publisher of MIT technology review and a founding member of the postscript school, delivered a presentation titled \"how smart companies can harness the future of technology.\"\n<br>\n    \"More and more intelligent hardware will be connected to the artificial intelligence Internet of things, communications Internet of things, we will always encounter privacy issues,\" he said.The simplest privacy problem is that the enterprise will collect a large amount of biological information of users on a terminal. If it is intelligent information, it will collect your voice text. If it is mobile phone face recognition and fingerprint recognition, it will collect your face and fingerprint information.These private data, if enterprises put it in the cloud for processing, then in fact the cloud will have all your private data, it will be in the cloud for private data firewall and cutting, this part is still blank in the law until now.That could be problematic under Europe\'s toughest privacy laws.\"');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `pid` varchar(45) NOT NULL,
  `uid` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`pid`,`uid`),
  KEY `fk_cart_product_idx` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES ('2019','17734567423',9),('21123','17731117423',14),('21123','17734567423',1),('2222','15911173468',1),('4413','17734567423',5),('4413','17801030028',3),('4444','15911173468',1),('5555','17734567423',1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `uid` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `passwordsalt` varchar(45) NOT NULL,
  `permission` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (' 000000','7960764caff4d577346758a7f7b017a9','aqsdmbqd',1,'Wu Renchao'),(' 666666','e5737749c4a278c143c03f940aa5eeba','ytxlyzll',1,'Xie Chenyang'),('111111','e40bb1e70212e3a7930421caf500b88a','vbexotbo',0,'Wei Yibin'),('123456','3fdde1bde2fc9f2bafbed592ca23e443','kliadoll',1,'lty'),('admin','2214f60d627a23b237b54eebede21cd3','rzhrsuzd',1,'admin'),('boss','d5025d1b2a555df9b0106a3c612dfc54','rvugkahe',1,'tom');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderinfo`
--

DROP TABLE IF EXISTS `orderinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderinfo` (
  `oid` varchar(45) NOT NULL,
  `uid` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `state` varchar(45) NOT NULL,
  `orderTime` datetime NOT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderinfo`
--

LOCK TABLES `orderinfo` WRITE;
/*!40000 ALTER TABLE `orderinfo` DISABLE KEYS */;
INSERT INTO `orderinfo` VALUES ('201909140129141536888975','17734567423','sadsadsad',2035.5,'paying','2019-09-14 05:29:15'),('201909140145571536888975','17734567423','sadsadsad',1015.5,'paying','2019-09-14 05:45:57'),('201909140153211536888975','17734567423','sadsadsad',1299.8400000000001,'paying','2019-09-14 05:53:21'),('201909140216481536888975','17734567423','sadsadsad',5455,'paying','2019-09-14 06:16:49'),('201909140221221536888975','17734567423','sadsadsad',99.9,'paying','2019-09-14 06:21:23'),('201909151041511536888975','17734567423','beijing',7153.87,'paying','2019-09-15 22:41:52'),('201909151103351536888975','17734567423','beijing',178.5,'paying','2019-09-15 23:03:35'),('201909161042011536888975','17734567423','beijing',3099.2999999999997,'paying','2019-09-16 10:42:02'),('201909170729331503487708','17801030028','123456',99.9,'paying','2019-09-17 19:29:34'),('201909170730011503487708','17801030028','123456',1776,'paying','2019-09-17 19:30:02'),('201909170730241503487708','17801030028','123456',888,'paying','2019-09-17 19:30:25'),('201909170731171503487708','17801030028','123456',999,'paying','2019-09-17 19:31:18'),('201909170820241503487708','17801030028','123456',1998,'paying','2019-09-17 20:20:25'),('201909170820421503487708','17801030028','123456',2664,'paying','2019-09-17 20:20:43'),('201909170839411864287166','15911173468','1122',5480.5,'paying','2019-09-17 20:39:42'),('201909170843341503487708','17801030028','123456',4440,'paying','2019-09-17 20:43:34'),('201909170849591503487708','17801030028','123456',650.97,'paying','2019-09-17 20:50:00'),('201909190259561503487708','17801030028','123456',999,'paying','2019-09-19 14:59:56'),('201909190301031503487708','17801030028','123456',89,'paying','2019-09-19 15:01:03'),('201909190330141536888975','17734567423','beijing',2799.86,'paying','2019-09-19 15:30:14'),('201909241101221536888975','17734567423','beijing',99.9,'paying','2019-09-24 23:01:22'),('201909250203131536888975','17734567423','beijing',25.5,'paying','2019-09-25 14:03:13'),('201909251048511503487708','17801030028','123456',1179.8,'paying','2019-09-25 10:48:51');
/*!40000 ALTER TABLE `orderinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitem`
--

DROP TABLE IF EXISTS `orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitem` (
  `pid` varchar(45) NOT NULL,
  `oid` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`pid`,`oid`),
  KEY `fk_orderitem_product1_idx` (`pid`),
  KEY `fk_orderitem_order1_idx` (`oid`),
  CONSTRAINT `fk_orderitem_order1` FOREIGN KEY (`oid`) REFERENCES `orderinfo` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitem`
--

LOCK TABLES `orderitem` WRITE;
/*!40000 ALTER TABLE `orderitem` DISABLE KEYS */;
INSERT INTO `orderitem` VALUES ('1125','201909251048511503487708',180,1),('12345','201909140129141536888975',37.5,3),('2019','201909190301031503487708',89,1),('21123','201909140129141536888975',1998,2),('21123','201909151041511536888975',999,1),('21123','201909170731171503487708',999,1),('21123','201909170820241503487708',999,2),('21123','201909190259561503487708',999,1),('2222','201909140216481536888975',5455,1),('2222','201909151041511536888975',5455,1),('2222','201909170839411864287166',5455,1),('4413','201909140145571536888975',888,1),('4413','201909170730011503487708',888,2),('4413','201909170730241503487708',888,1),('4413','201909170820421503487708',888,3),('4413','201909170843341503487708',888,5),('4444','201909140153211536888975',99.9,1),('4444','201909140221221536888975',99.9,1),('4444','201909151041511536888975',99.9,1),('4444','201909161042011536888975',99.9,1),('4444','201909170729331503487708',99.9,1),('4444','201909241101221536888975',99.9,1),('5555','201909140153211536888975',1199.94,6),('5555','201909151041511536888975',199.99,3),('5555','201909170849591503487708',199.99,3),('5555','201909190330141536888975',199.99,14),('6666','201909161042011536888975',999.8,3),('6666','201909251048511503487708',999.8,1),('7777','201909140145571536888975',127.5,5),('7777','201909151103351536888975',25.5,7),('7777','201909170839411864287166',25.5,1),('7777','201909170849591503487708',25.5,2),('7777','201909250203131536888975',25.5,1);
/*!40000 ALTER TABLE `orderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `pid` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `stock` int(11) NOT NULL,
  `shortdesc` varchar(80) NOT NULL,
  `state` varchar(45) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('1125','r.a.d. robot ',180,8,'Remote control robot toy for children','hot'),('123','Sphero R2-D2',188,10,'Supported application robots','hot'),('1234','Anki Cozmo',2880,8,'touch control character Harley robot','ok'),('1753','WowWee MiP',520,5,'Toy Robot Force','ok'),('1755','R-robot',88,6,'Interactive boxing robot','hot'),('2019','Guide dog S',89,19,'A guide robot with automatic pathfinding and obstacle avoidance functions','hot'),('21123','T777',999,0,'great robot','ok'),('4413','robot t',888,0,'NO robot best!','ok'),('4444','dog',99.9,99,'a robot dog','hot'),('5555','smart robot V',199.99,97,'smart robot VVVVVV','hot'),('55555','robot xx',556,98,'x model! new model','ok'),('6666','robot T',999.8,119,'smart robot/help people','ok'),('7777','robot mi 8s',25.5,4,'mi 8s, best robot!','hot'),('88991','robot neo',12399,1,'robot neo, the hard bot','ok');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uid` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `passwordSalt` varchar(45) NOT NULL,
  `nickname` varchar(45) NOT NULL,
  `birthday` date NOT NULL,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('100000','d17e405fa25a085b932f600d62e5ee0a','mbmair','100000','2019-10-09','100000'),('123456','3c1fd4fd06dcaff7fe95b32f7ec02a71','abcd','qweqwe','1998-03-12','qqqqqq'),('12828482838','3617efdf3a2da2701df3ddb6a9be6b85','vxuxsi','sadadsa','1998-01-09','asdjlkfsafdasdda'),('13933873551','072b0ba9c19394dd6cfb97ee8617c1c8','wvrrli','lujun','2018-12-31','jlksdfjdsjlkfjlkdsq'),('15103119952','12323','sdad','ycy','1999-01-18','asdsdasd'),('15511308633','9912334','12313s','lty','1999-08-24','sdkjfjdsklfsjlkafjlk'),('15911173468','53e0406aef7687ea5be8da9a8fc277e2','celshd','123123','2001-01-01','1122'),('17731113333','86728bf62c1494bcca643f8d529edcc1','ygwpja','12313','2000-12-31','1231312312'),('17731117423','85a4004d8ef9e1e42812447e2710d47b','dsdcdj','qq','1998-01-23','bupt'),('17734565555','c685c7d7ce012d432f27c91008899c1c','vtstcx','ahdhdd','2001-01-01','hddjksk'),('17734567111','45d9bf3c9eaae473f5650860ecbe6e2b','zfnppd','awsdad','1998-12-31','19991323213'),('17734567421','7cf085d69afc9559e5e9dfd221367fb3','nytvvf','asdasdad','1999-11-10','sasdasd'),('17734567423','a7885bd276ec7b2484ecb87418cb4382','dxvvwk','icebound','1999-08-20','beijing'),('17737373737','d443a1ef17563689eefe5ca14c5d31f3','ilmgrr','asdasd','2000-12-31','sdfdsfs'),('17746463636','a008868361f23da036654c364ea7fd65','zuyfoy','asdasdd','1988-11-30','bupt-xtc'),('17801030028','5762f20ae1c8a2501127ecfc48b81a22','grgphv','123qwe','1999-01-01','123456'),('18282818212','7bd5795c9716322a2fa57594f2792c62','dnbgnp','asdsadd','2000-12-31','ashjkdhjksjkdshjkd'),('18282818282','90f43b2539b0ea9610722826a0b5b8fa','ubizdc','agsghd','2000-12-31','asdasdsadasd'),('18282828281','0945e157345f50f044ffe0c036b11401','whdmfz','kjsfdhasd','2000-12-31','asdasdasd'),('18282828282','8616bc960a22d013d3f7649a64156cf8','ejzazr','asdsad','2001-10-31','1213213123'),('18822223333','fd383a2e53c4daca42019f32c045b943','wfaayg','asdadas','1999-11-10','11112213'),('18833218742','c1b6395ee5d4774ea366aaa46ae682eb','xuzcdw','sad','2000-12-31','asdsddad'),('18833332222','1d31514654d3b57858a63ea93ed8175a','btgtbp','asdasdadas','1998-12-31','    alert(\"!!!\");'),('18894842838','ba766bde9fccde4993f0e513028be21c','hbfobv','asdas','1231-12-11','sdkjlfdslf'),('18911151233','eb70a71a720e12d39a90a76a59197e6e','chttbe','yinmeiren','1999-01-18','ustb'),('9129392193','123','234','33123','1999-03-12','sdsdd');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-25 21:07:08
