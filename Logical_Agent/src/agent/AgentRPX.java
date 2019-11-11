//package agent;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//public class AgentRPX {
//	static char[][] Mymap;
//	static char[][] orignal;
//	static int tornedoNum=3;
//
//	AgentRPX(char[][] map)
//	{
//		AgentRPX.orignal=map;
//
//		Mymap=new char[map.length][map.length];
//
//		for (int i=0;i<map.length;i++)
//		{
//			for (int j=0;j<map.length;j++)
//			{
//				Mymap[i][j]='*';
//			}
//		}
//		Board agentmap=new Board(Mymap);
//	}
//
//	public char[][] getMymap () {
//		return this.Mymap;
//	}
//
//	public static void start()
//	{
//
//		Cell midCell=new Cell(2,2,'0');
//
//
//		Cell top=new Cell(0,0,orignal[0][0]);
//
//		A2main.agentBoard.printBoard();//printing initial board
//		
//		System.out.println("starting the probing now--------");
//  
//		KnowledgeBase.tobeProbe.remove(midCell);
//		//KnowledgeBase.tobeProbe.remove(top);
//		KnowledgeBase.alreadyProbed.add(midCell);
//		//KnowledgeBase.alreadyProbed.add(top);
//		
//		
//		
//		check_valueEqualsZero(midCell);
//		
//
//		System.out.println("probing the top value 0,0");
//
////		check_valueEqualsZero(top);
//
//		
//
//		System.out.println("Randomly Probing a cell now");
//		next_move();
//	}
//	@SuppressWarnings("static-access")
//	public static void check_valueEqualsZero(Cell cell1)
//	{
//		char value1='*';
//		char value2 ='*';
//		char value3='*';
//		char value4='*';
//		char value5='*';
//		char value6='*';
//
//
//		if (cell1.value=='0')
//		{
//			if (cell1.x<AgentRPX.Mymap.length-1)
//			{
//				value1=AgentRPX.orignal[cell1.x+1][cell1.y];
//				Cell c=new Cell(cell1.x+1,cell1.y,value1);
//				
//				KnowledgeBase.tobeProbe.remove(c);
//				KnowledgeBase.alreadyProbed.add(c);
//	            
//				if (c.value=='0' && KnowledgeBase.validateZero(c)==false && KnowledgeBase.validatezeroList(c)==false)
//				{
//					System.out.println("1 is being added########"+c.value+c.x+c.y);
//					KnowledgeBase.zeros.add(c);
//					
//				}
//
//			}
//			if (cell1.x>0)
//			{
//				value2=AgentRPX.orignal[cell1.x-1][cell1.y];
//				Cell c=new Cell(cell1.x-1,cell1.y,value2);
//				
//				if (KnowledgeBase.validateProbeList(c)==true)
//	               {
//				KnowledgeBase.tobeProbe.remove(c);
//				KnowledgeBase.alreadyProbed.add(c);
//	               }
//				if (c.value=='0' && KnowledgeBase.validateZero(c)==false && KnowledgeBase.validatezeroList(c)==false)
//				{
//					System.out.println("2 is being added########"+c.value+c.x+c.y);
//					KnowledgeBase.zeros.add(c);
//				}
//
//			}
//			if (cell1.y<AgentRPX.Mymap.length-1)
//			{
//				value3=AgentRPX.orignal[cell1.x][cell1.y+1];
//				Cell c=new Cell(cell1.x,cell1.y+1,value3);
//				
//				KnowledgeBase.tobeProbe.remove(c);
//				KnowledgeBase.alreadyProbed.add(c);
//	               
//				if (c.value=='0' && KnowledgeBase.validateZero(c)==false && KnowledgeBase.validatezeroList(c)==false)
//				{
//					System.out.println("3 is being added########"+c.value+c.x+c.y);
//					KnowledgeBase.zeros.add(c);
//				}
//
//			}
//			if (cell1.y>0)
//			{
//				value4=AgentRPX.orignal[cell1.x][cell1.y-1];
//				Cell c=new Cell(cell1.x,cell1.y-1,value4);
//				
//				KnowledgeBase.tobeProbe.remove(c);
//				KnowledgeBase.alreadyProbed.add(c);
//                
//				
//				if (c.value=='0' && KnowledgeBase.validateZero(c)==false && KnowledgeBase.validatezeroList(c)==false)
//				{
//					System.out.println("4 is being added########"+c.value+c.x+c.y);
//					KnowledgeBase.zeros.add(c);
//				}
//
//			}
//			if (cell1.x<AgentRPX.Mymap.length-1 && cell1.y<AgentRPX.Mymap.length-1)
//			{
//				value5=AgentRPX.orignal[cell1.x+1][cell1.y+1];
//				Cell c=new Cell(cell1.x+1,cell1.y+1,value5);
//				
//				KnowledgeBase.tobeProbe.remove(c);
//				KnowledgeBase.alreadyProbed.add(c);
//                
//				
//				if (c.value=='0' && KnowledgeBase.validateZero(c)==false && KnowledgeBase.validatezeroList(c)==false && KnowledgeBase.validateProbeList(c)==true)
//				{
//					System.out.println("5 is being added########"+c.value+c.x+c.y);
//					KnowledgeBase.zeros.add(c);
//				}
//
//			}
//			if (cell1.x>0 && cell1.y>0)
//			{
//				value6=AgentRPX.orignal[cell1.x-1][cell1.y-1];
//				Cell c=new Cell(cell1.x-1,cell1.y-1,value6);
//				KnowledgeBase.tobeProbe.remove(c);
//				KnowledgeBase.alreadyProbed.add(c);
//                
//				if (c.value=='0' && KnowledgeBase.validateZero(c)==false && KnowledgeBase.validatezeroList(c)==false)
//			{
//					System.out.println("6 is being added########"+c.value+c.x+c.y);
//					KnowledgeBase.zeros.add(c);
//			
//			}
//					
//					//KnowledgeBase.zerosExpanded.add(cell1);
//			
//			}
//
//			AgentRPX.Mymap[cell1.x][cell1.y]=cell1.value;
//			if (cell1.x<AgentRPX.Mymap.length-1) 
//			{
//				AgentRPX.Mymap[cell1.x+1][cell1.y]=value1;
//			}
//			if (cell1.x>0) {
//				AgentRPX.Mymap[cell1.x-1][cell1.y]=value2;
//			}
//			if (cell1.y<AgentRPX.Mymap.length-1) 
//			{
//				AgentRPX.Mymap[cell1.x][cell1.y+1]=value3;
//			}
//			if (cell1.y>0)
//			{
//				AgentRPX.Mymap[cell1.x][cell1.y-1]=value4;
//			}
//			if (cell1.x<AgentRPX.Mymap.length-1 && cell1.y<AgentRPX.Mymap.length-1)
//			{
//				AgentRPX.Mymap[cell1.x+1][cell1.y+1]=value5;
//			}
//			if (cell1.x>0 && cell1.y>0)
//			{
//				AgentRPX.Mymap[cell1.x-1][cell1.y-1]=value6;	
//			}
//			A2main.agentBoard.printBoard(); 
//		}
//		else if (cell1.value>0)
//		{
//			AgentRPX.Mymap[cell1.x][cell1.y]=cell1.value;
//
//			KnowledgeBase.tobeProbe.remove(cell1);
//			KnowledgeBase.alreadyProbed.add(cell1);
//			A2main.agentBoard.printBoard();
//		}
//
//          
//          
//          for (int i=0; i<KnowledgeBase.zeros.size();i++)
//			{
//        	  
//        	  
//        	  System.out.println("#######"+ KnowledgeBase.zeros.get(i).x+ KnowledgeBase.zeros.get(i).y+"value:"+KnowledgeBase.zeros.get(i).value);
//			}
//			
//          //KnowledgeBase.printzero();
//          //KnowledgeBase.printzeroExpand();
//          checkZero(); 
//          
//          
//         
//	}
//	//--------------------------------------randomly selecting a move-----------------------------------
//
//	public static void next_move()
//	{
//			
//		for (int i=0; i<KnowledgeBase.tobeProbe.size(); i++)
//		{
//			if (KnowledgeBase.tobeProbe.size()!=
//					tornedoNum)
//			{
//
//				Cell randomCell = KnowledgeBase.tobeProbe.get(new Random().nextInt(KnowledgeBase.tobeProbe.size()));
//				while(KnowledgeBase.validateProbeList(randomCell)==true)
//				{
//					KnowledgeBase.tobeProbe.remove(randomCell);
//					KnowledgeBase.alreadyProbed.add(randomCell);
//					randomCell = KnowledgeBase.tobeProbe.get(new Random().nextInt(KnowledgeBase.tobeProbe.size()));
//				}
//
//				System.out.println("Call randomly probed is"+ randomCell.x+","+randomCell.y+ " and the value of this move is: "+randomCell.value);
//
//				if (randomCell.value=='t')
//				{System.out.println("you have probed a tornado, hence Game Over");
//
//				AgentRPX.Mymap[randomCell.x][randomCell.y]=randomCell.value;
//
//				A2main.agentBoard.printBoard();
//
//				System.exit(0);
//
//
//				}
//
//				else if (randomCell.value=='0' || randomCell.value>0)
//				{
//					
//					check_valueEqualsZero(randomCell);
//					
//
//				}
//			}
//
//			else if ((KnowledgeBase.tobeProbe.size()==tornedoNum))
//			{   
//				
//				System.out.print("game won");
//			
//			}
//
//		}
//
//
//	}
//	
//	public static void checkZero()
//	{
//		
//		     List<Cell> copyOfZero = new ArrayList<>();
//		     
//		  //-------creating a copy of the list of zeros for iteration   
//		     
//				for (int i=0; i<KnowledgeBase.zeros.size();i++)
//				{
//					copyOfZero.add(KnowledgeBase.zeros.peek());
//						
//				}
//			
//				
//			for (int i=0; i<copyOfZero.size();i++)
//			{
//		     
//		            Cell cell= copyOfZero.get(i);	
//
//					if (KnowledgeBase.validateZero(cell)==false)
//					{
//					System.out.println("inside validation for position"+ copyOfZero.get(i).x+ copyOfZero.get(i).y);
//					
//				    KnowledgeBase.zerosExpanded.add(cell);
//					
//				    KnowledgeBase.zeros.remove(cell);	
//					check_valueEqualsZero(cell);
//					
//					}
//					
//			
//			}
//			KnowledgeBase.zeros.clear();
//    
//		
//		
//	}
//	
//}
