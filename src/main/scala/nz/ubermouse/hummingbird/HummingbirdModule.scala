package nz.ubermouse.hummingbird

import scaldi.Module

class HummingbirdModule extends Module {
  bind [HttpInteractions] to injected[HummingbirdHttpInteractions]
  bind [Hummingbird] to injected[Hummingbird]
}
