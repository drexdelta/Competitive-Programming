package com.codechef.competitions.shortcompetitions.year2015.june.cookoff;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.TreeSet;

class Second
{
	private static int t, k;
	private static String s;
	private static InputReader reader;
	private static OutputWriter writer;
	
	public static void main(String[] args)
	{
		Second second = new Second();
		
		reader = second.new InputReader(System.in);
		writer = second.new OutputWriter(System.out);
		
/*		String b = "abcd";
		System.out.println(b.substring(1, 1));
		
		int length = b.length();
		String a;
		int del = 0;
		ArrayList<String> subseq = new ArrayList<String>();
		
		for (int j = 0; j < length; j++)
		{
			a = b.substring(0, del);
			a += b.substring(del + 1, length);
			
			subseq.add(a);
			
			del++;
		}
		
		System.out.println(subseq.toString());*/
		
/*		writer.println("with 1 : ");
		writer.flush();
		getAttributes();
		
		writer.println("with 2 : ");
		writer.flush();*/
		getAttributes2();
		
		writer.flush();
		
		reader.close();
		writer.close();
	}
	
	public static void getAttributes()
	{
		ArrayList<String> subseq = new ArrayList<>();
		int length;
		
/*		subseq.add("shfjdjf");
		subseq.add("sjdfdoijknah");
		subseq.add("jljaf");
		subseq.add("aajdflj");
		
		subseq.sort(null);
		
		System.out.println(subseq.toString());*/
		
		t = reader.nextInt();
		
		for (int i = 0; i < t; i++)
		{
			s = reader.next();
			k = reader.nextInt();
			
			subseq = new ArrayList<String>();
			
			length = s.length();
			
			if (length % 2 == 1 || (s.charAt(length - 1) == '(' || s.charAt(0) == ')'))
			{
				if (k == 1)
					writer.println(s);
				else
					writer.println(-1);
			}
			else
			{
				if (k > length)
					writer.println(-1);
				else
				{
					if (!isValid(s))
					{
						if (k == 1)
							writer.println(s);
						else
							writer.println(-1);
						
						continue;
					}
					
					String a;
					int del = 0;
					
					for (int j = 0; j < length; j++)
					{
						a = s.substring(0, del);
						a += s.substring(del + 1, length);
						
						if (!subseq.contains(a))
							subseq.add(a);
						
						del++;
					}
					
					subseq.sort(null);
					
					// Object[] sA = subseq.get(k - 1);toArray();
					
					if (k <= subseq.size())
						writer.println(subseq.get(k - 1));
					else
						writer.println(-1);
				}
			}
		}
	}
	
	public static void getAttributes2()
	{
		TreeSet<String> subseq = new TreeSet<String>();
		int length;
		
		t = reader.nextInt();
		
		for (int i = 0; i < t; i++)
		{
			s = reader.next();
			k = reader.nextInt();
			
			subseq = new TreeSet<String>();
			
			length = s.length();
			
			if (length % 2 == 1 || (s.charAt(length - 1) == '(' || s.charAt(0) == ')'))
			{
				if (k == 1)
					writer.println(s);
				else
					writer.println(-1);
			}
			else
			{
				if (k > length)
					writer.println(-1);
				else
				{
					if (!isValid(s))
					{
						if (k == 1)
							writer.println(s);
						else
							writer.println(-1);
						
						continue;
					}
					
					String a;
					int del = 0;
					
					for (int j = 0; j < length; j++)
					{
						a = s.substring(0, del);
						a += s.substring(del + 1, length);
						
						if (!subseq.contains(a))
							subseq.add(a);
						
						del++;
					}
					
					if (k <= subseq.size())
						writer.println(subseq.toArray()[k - 1].toString());
					else
						writer.println(-1);
				}
			}
		}
	}
	
	public static boolean isValid(String a)
	{
		int len, opened, closed;
		
		len = a.length();
		opened = closed = 0;
		
		for (int i = 0; i < len; i++)
		{
			if (a.charAt(i) == '(')
				opened++;
			else
				opened--;
			
			if (closed > opened)
				return false;
		}
		
		if (opened > 0)
			return false;
		
		return true;
	}
	
	class InputReader
	{
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;

		public InputReader(InputStream stream)
		{
			this.stream = stream;
		}

		public int read()
		{
			if (numChars == -1)
				throw new InputMismatchException();
			
			if (curChar >= numChars)
			{
				curChar = 0;
				try
				{
					numChars = stream.read(buf);
				}
				catch (IOException e)
				{
					throw new InputMismatchException();
				}
				if (numChars <= 0)
					return -1;
			}
			
			return buf[curChar++];
		}

		public int nextInt()
		{
			int c = read();
			
			while (isSpaceChar(c))
				c = read();
			
			int sgn = 1;
			
			if (c == '-')
			{
				sgn = -1;
				c = read();
			}
			
			int res = 0;
			
			do
			{
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				
				res *= 10;
				res += c & 15;
				
				c = read();
			} while (!isSpaceChar(c));
			
			return res * sgn;
		}

		public long nextLong()
		{
			int c = read();
			
			while (isSpaceChar(c))
				c = read();
			
			int sign = 1;
			
			if (c == '-')
			{
				sign = -1;
				
				c = read();
			}
			
			long result = 0;
			
			do
			{
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				
				result *= 10;
				result += c & 15;
				
				c = read();
			} while (!isSpaceChar(c));
			
			return result * sign;
		}
		
		public float nextFloat() // problematic
		{
			float result, div;
			byte c;

			result = 0;
			div = 1;
			c = (byte) read();

			while (c <= ' ')
				c = (byte) read();

			boolean isNegative = (c == '-');

			if (isNegative)
				c = (byte) read();

			do
			{
				result = result * 10 + c - '0';
			} while ((c = (byte) read()) >= '0' && c <= '9');

			if (c == '.')
				while ((c = (byte) read()) >= '0' && c <= '9')
					result += (c - '0') / (div *= 10);

			if (isNegative)
				return -result;

			return result;
		}

		public double nextDouble() // not completely accurate
		{
			double ret = 0, div = 1;
			byte c = (byte) read();

			while (c <= ' ')
				c = (byte) read();

			boolean neg = (c == '-');

			if (neg)
				c = (byte) read();

			do
			{
				ret = ret * 10 + c - '0';
			} while ((c = (byte) read()) >= '0' && c <= '9');

			if (c == '.')
				while ((c = (byte) read()) >= '0' && c <= '9')
					ret += (c - '0') / (div *= 10);

			if (neg)
				return -ret;

			return ret;
		}

		public String next()
		{
			int c = read();
			
			while (isSpaceChar(c))
				c = read();
			
			StringBuilder res = new StringBuilder();
			
			do
			{
				res.appendCodePoint(c);
				
				c = read();
			} while (!isSpaceChar(c));
			
			return res.toString();
		}

		public boolean isSpaceChar(int c)
		{
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public void close()
		{
			try
			{
				stream.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}

	class OutputWriter
	{
		private PrintWriter writer;

		public OutputWriter(OutputStream stream)
		{
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					stream)));
		}

		public OutputWriter(Writer writer)
		{
			this.writer = new PrintWriter(writer);
		}

		public void println(int x)
		{
			writer.println(x);
		}

		public void print(int x)
		{
			writer.print(x);
		}
		
		public void println(int array[], int size)
		{
			for (int i = 0; i < size; i++)
				println(array[i]);
		}
		
		public void print(int array[], int size)
		{
			for (int i = 0; i < size; i++)
				print(array[i] + " ");
		}

		public void println(long x)
		{
			writer.println(x);
		}

		public void print(long x)
		{
			writer.print(x);
		}
		
		public void println(long array[], int size)
		{
			for (int i = 0; i < size; i++)
				println(array[i]);
		}
		
		public void print(long array[], int size)
		{
			for (int i = 0; i < size; i++)
				print(array[i]);
		}
		
		public void println(float num)
		{
			writer.println(num);
		}
		
		public void print(float num)
		{
			writer.print(num);
		}
		
		public void println(double num)
		{
			writer.println(num);
		}
		
		public void print(double num)
		{
			writer.print(num);
		}

		public void println(String s)
		{
			writer.println(s);
		}

		public void print(String s)
		{
			writer.print(s);
		}
		
		public void println()
		{
			writer.println();
		}

		public void printSpace()
		{
			writer.print(" ");
		}

		public void flush()
		{
			writer.flush();
		}

		public void close()
		{
			writer.close();
		}

	}

}
