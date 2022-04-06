resource "kubernetes_namespace" "adjust" {
  metadata {
        name = "adjust"
  }
}