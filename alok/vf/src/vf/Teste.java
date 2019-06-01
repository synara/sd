package vf;

public class Teste {
	public static void main(String[] args) {
		Grafo grafo = new Grafo(4);
		
		grafo.inserirAresta(0, 1, 5);
		grafo.inserirAresta(0, 2, 3);
		grafo.inserirAresta(1, 3, 7);
		grafo.inserirAresta(2, 1, 4);
		
		grafo.CaminhoMinimo(0);
		System.out.println("");
		grafo.Dijkstra(0);
		System.out.println("");
		grafo.Prim();
	}
}

