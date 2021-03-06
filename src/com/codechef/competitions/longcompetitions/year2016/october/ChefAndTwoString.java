package com.codechef.competitions.longcompetitions.year2016.october;

import java.io.*;
import java.util.*;

public final class ChefAndTwoString
{
    public static void main(String[] args)
    {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
		Solver solver = new Solver(in, out);

//		solver.solve2();
		solver.solve();
        in.close();
        out.flush();
        out.close();
    }

    static class Solver
    {
		static final long mod = (long) 1e9 + 7;
        int t, n;
		String zeroes;
		String a, b;
        InputReader in;
        OutputWriter out;

		void solve()
		{
			t = in.nextInt();

			while (t-- > 0)
			{
				a = in.next();
				b = in.next();


			}
		}

		void solve3()
		{
			t = in.nextInt();
			zeroes = "0000000000000000000";

			while (t-- > 0)
			{
				StringBuilder a, b;

				a = new StringBuilder(in.next());
				b = new StringBuilder(in.next());

				out.println(count(a, b));
			}
		}

		void solve2()
		{
			for (int i = 0; i < 1; i++)
			{
				random();
			}
		}

		void random()
		{
			n = (int) (Math.random() * 10) + 1;

			StringBuilder a, b;

			a = createString(n);
			b = createString(n);

//			a = new StringBuilder("12212");
//			b = new StringBuilder("12212");
//			a = new StringBuilder("12212212");
//			b = new StringBuilder("12212211");
			a = new StringBuilder("112112");
			b = new StringBuilder("111212");
//			a = new StringBuilder("1111");
//			b = new StringBuilder("2211");

			System.out.println("a : " + a + ", b : " + b);
			System.out.println("ways : " + count(a, b));
		}

		long count(StringBuilder a, StringBuilder b)
		{
			int len = a.length();
			long count = 0;

			for (int i = 0; i < (1 << len); i++)
			{
				if (check(a, b, Integer.toBinaryString(i)))
					count++;
			}

			return count;
		}

		boolean check(StringBuilder a, StringBuilder b, String bin)
		{
			int len = a.length();

			bin = zeroes.substring(0, len - bin.length()) + bin;

			for (int i = 0; i < len; i++)
			{
				if (bin.charAt(i) == '1')
				{
					char temp = a.charAt(i);

					a.setCharAt(i, b.charAt(i));
					b.setCharAt(i, temp);
				}
			}

			boolean result = poss(a) && poss(b);

			for (int i = 0; i < len; i++)
			{
				if (bin.charAt(i) == '1')
				{
					char temp = a.charAt(i);

					a.setCharAt(i, b.charAt(i));
					b.setCharAt(i, temp);
				}
			}

			return result;
		}

		boolean poss(StringBuilder s)
		{
			int len = s.length();
			boolean[] vis = new boolean[len];
			int curr = 0;
			int hops = 0;
			int visited = 0;

			while (hops < len)
			{
				if (vis[curr])
					return false;

				vis[curr] = true;
				visited++;

				int left = curr - (s.charAt(curr) - '0');
				int right = curr + (s.charAt(curr) - '0');

				if (left >= 0 && !vis[left])
				{
					curr = left;
					hops++;
				}
				else if (right < len && !vis[right])
				{
					curr = right;
					hops++;
				}

				if (curr == len - 1 && visited == len)
					return true;
			}


			if (hops == len && visited == len && curr == len - 1)
				return true;

			return false;
		}

		StringBuilder createString(int len)
		{
			StringBuilder sb = new StringBuilder("");

			for (int i = 0; i < len; i++)
				sb.append((int) (Math.random() * 2) + 1);

			return sb;
		}

        public Solver(InputReader in, OutputWriter out)
        {
        	this.in = in;
        	this.out = out;
        }

	}

    static class InputReader
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
                } catch (IOException e)
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

        public int[] nextIntArray(int arraySize)
        {
            int array[] = new int[arraySize];

            for (int i = 0; i < arraySize; i++)
                array[i] = nextInt();

            return array;
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

        public long[] nextLongArray(int arraySize)
        {
            long array[] = new long[arraySize];

            for (int i = 0; i < arraySize; i++)
                array[i] = nextLong();

            return array;
        }

        public float nextFloat()
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

        public double nextDouble()
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

        public String nextLine()
        {
            int c = read();

            StringBuilder result = new StringBuilder();

            do
            {
                result.appendCodePoint(c);

                c = read();
            } while (!isNewLine(c));

            return result.toString();
        }

        public boolean isNewLine(int c)
        {
            return c == '\n';
        }

        public void close()
        {
            try
            {
                stream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    static class OutputWriter
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

		public void println(char x)
		{
			writer.println(x);
		}

		public void print(char x)
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

		public void printf(String format, Object args)
		{
			writer.printf(format, args);
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

	static class CMath
	{
		static long power(long number, long power)
		{
			if (number == 1 || number == 0 || power == 0)
				return 1;

			if (power == 1)
				return number;

			if (power % 2 == 0)
				return power(number * number, power / 2);
			else
				return power(number * number, power / 2) * number;
		}

		static long modPower(long number, long power, long mod)
		{
			if (number == 1 || number == 0 || power == 0)
				return 1;

			number = mod(number, mod);

			if (power == 1)
				return number;

			long square = mod(number * number, mod);

			if (power % 2 == 0)
				return modPower(square, power / 2, mod);
			else
				return mod(modPower(square, power / 2, mod) * number, mod);
		}

		static long moduloInverse(long number, long mod)
		{
			return modPower(number, mod - 2, mod);
		}

		static long mod(long number, long mod)
		{
			return number - (number / mod) * mod;
		}

		static int gcd(int a, int b)
		{
			if (b == 0)
				return a;
			else
				return gcd(b, a % b);
		}

	}

}
